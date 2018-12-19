package io.infinite.carburetor

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.inspect.swingui.AstNodeToScriptVisitor
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.carburetor.ast.MetaDataExpression
import io.infinite.carburetor.ast.MetaDataMethodNode
import io.infinite.carburetor.ast.MetaDataStatement
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.classgen.VariableScopeVisitor
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.runtime.StackTraceUtils
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression
import org.slf4j.MDC

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
@Slf4j
abstract class CarburetorTransformation extends AbstractASTTransformation {

    AnnotationNode annotationNode
    CarburetorLevel carburetorLevel
    private static Integer uniqueClosureParamCounter = 0
    CarburetorConfig carburetorConfig = initCarburetorConfig()

    static {
        ASTNode.getMetaClass().origCodeString = null
        ASTNode.getMetaClass().transformedBy = null
    }

    void report(String msg) {
        log.info("Carburator: " + Thread.currentThread().getName() + ": " + msg)
    }

    CarburetorConfig initCarburetorConfig() {
        CarburetorConfig carburetorConfig
        MDC.put("unitName", "CARBURETOR_INIT")
        if (new File("./Carburetor.json").exists()) {
            carburetorConfig = new ObjectMapper().readValue(new File("./Carburetor.json").getText(), CarburetorConfig.class)
            report("Global level: " + carburetorConfig.getDefaultLevel())
            report("Levels by transformation implementation: " + carburetorConfig.getLevelsByImplementingClass().toString())
            report("Transformation implementation: " + getClass().getCanonicalName())
            report("Level by transformation implementation: " + carburetorConfig.getLevelsByImplementingClass().get(getClass().getCanonicalName()))
        } else {
            carburetorConfig = new CarburetorConfig()
            report("Carburetor.json not found at " + new File("./").getCanonicalPath())
            report("Using default level: " + carburetorConfig.getDefaultLevel())
        }
        return carburetorConfig
    }

    abstract Boolean excludeMethodNode(MethodNode methodNode)

    void visitClassNode(ClassNode classNode) {
        classDeclarations(classNode)
        classNode.methods.each {
            if (!(it.isAbstract() || excludeMethodNode(it))) {
                visitMethod(it)
            }
        }
    }

    abstract void classDeclarations(ClassNode classNode)

    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        try {
            init(iAstNodeArray, iSourceUnit)
            annotationNode = iAstNodeArray[0] as AnnotationNode
            if (iAstNodeArray[1] instanceof MethodNode) {
                visitMethod(iAstNodeArray[1] as MethodNode)
            } else if (iAstNodeArray[1] instanceof ClassNode) {
                visitClassNode(iAstNodeArray[1] as ClassNode)
            } else {
                throw new CarburetorCompileException(iAstNodeArray[1], "Unsupported Annotated Node; Only [Class, Method, Constructor] are supported.", this)
            }
        } catch (Exception exception) {
            log.error(ExceptionUtils.getStackTrace(new StackTraceUtils().sanitize(exception)))
            throw exception
        }
    }

    abstract void methodDeclarations(MethodNode methodNode)

    void visitMethod(MethodNode methodNode) {
        try {
            if (methodNode.transformedBy == this) {
                return
            }
            if (methodNode.getDeclaringClass().getOuterClass() != null) {
                throw new CarburetorCompileException(methodNode, "Carburetor currently does not support annotations in Inner Classes.", this)
            }
            if (methodNode.isAbstract()) {
                throw new CarburetorCompileException(methodNode, "Carburetor does not support annotation of Abstract Methods", this)
            }
            if (codeString(methodNode.getCode()).contains(getEngineVarName())) {
                throw new CarburetorCompileException(methodNode, "Duplicate transformation", this)
            }
            methodDeclarations(methodNode)
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            MDC.put("unitName", "CARBURETOR_$className.${methodName.replace("<", "").replace(">", "")}")
            carburetorLevel = getAnnotationParameter("level", carburetorConfig.getLevel(annotationNode.getClassNode().getName())) as CarburetorLevel
            getAnnotationParameters()
            transformMethod(methodNode)
            sourceUnit.AST.classes.each {
                new VariableScopeVisitor(sourceUnit, true).visitClass(it)
            }
            log.debug(codeString(methodNode.getCode()))
            methodNode.transformedBy = this
        } catch (Exception exception) {
            log.error(ExceptionUtils.getStackTrace(new StackTraceUtils().sanitize(exception)))
            throw new CarburetorCompileException(methodNode, exception, this)
        }
    }

    abstract void getAnnotationParameters()

    Object getAnnotationParameter(String annotationName, Object defaultValue) {
        Expression memberExpression = annotationNode.getMember(annotationName)
        if (memberExpression instanceof PropertyExpression) {
            ConstantExpression constantExpression = memberExpression.getProperty() as ConstantExpression
            return constantExpression.getValue()
        } else if (memberExpression instanceof ConstantExpression) {
            return memberExpression.getValue()
        } else if (memberExpression == null) {
            return defaultValue
        } else {
            throw new CarburetorCompileException(memberExpression, "Unsupported annotation \"$annotationName\" member expression class: " + memberExpression.getClass().getCanonicalName() + " for method " + MDC.get("unitName"), this)
        }
    }

    ClosureExpression wrapStatementIntoClosure(Statement statement) {
        ClosureExpression closureExpression = GeneralUtils.closureX(
                GeneralUtils.params(
                        GeneralUtils.param(ClassHelper.make(Object.class), "itVariableReplacement" + uniqueClosureParamCounter)
                ),
                statement
        )
        uniqueClosureParamCounter++
        closureExpression.transformedBy = statement.transformedBy
        return closureExpression
    }

    MethodCallExpression wrapExpressionIntoMethodCallExpression(Expression expression, sourceNodeName) {
        ClosureExpression closureExpression = wrapStatementIntoClosure(GeneralUtils.returnS(expression))
        closureExpression.setVariableScope(new VariableScope())
        MethodCallExpression methodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "expressionEvaluation",
                GeneralUtils.args(
                        GeneralUtils.ctorX(
                                ClassHelper.make(MetaDataExpression.class),
                                GeneralUtils.args(
                                        GeneralUtils.constX(expression.getClass().getSimpleName()),
                                        GeneralUtils.constX(expression.origCodeString),
                                        GeneralUtils.constX(expression.getColumnNumber()),
                                        GeneralUtils.constX(expression.getLastColumnNumber()),
                                        GeneralUtils.constX(expression.getLineNumber()),
                                        GeneralUtils.constX(expression.getLastLineNumber()),
                                        GeneralUtils.constX(sourceNodeName)
                                )
                        ),
                        closureExpression,
                        GeneralUtils.varX("automaticThis")
                )
        )
        methodCallExpression.copyNodeMetaData(expression)
        methodCallExpression.setSourcePosition(expression)
        methodCallExpression.transformedBy = this
        return methodCallExpression
    }

    Statement checkSuperConstructorCall(MethodNode iMethodNode) {
        Statement firstStatement = new EmptyStatement()
        if (iMethodNode instanceof ConstructorNode) {
            def initialFirstStatement = ((BlockStatement) iMethodNode.getCode()).getStatements().get(0)
            if (initialFirstStatement instanceof ExpressionStatement) {
                if (initialFirstStatement.getExpression() instanceof ConstructorCallExpression) {
                    if (((ConstructorCallExpression) initialFirstStatement.getExpression()).isSuperCall()) {
                        firstStatement = initialFirstStatement
                        ((BlockStatement) iMethodNode.getCode()).getStatements().remove(initialFirstStatement)
                    }
                }
            }
        }
        return firstStatement
    }

    abstract String getEngineVarName()

    abstract Statement createEngineDeclaration()

    void transformMethod(MethodNode iMethodNode) {
        if (carburetorLevel.value() == CarburetorLevel.NONE.value()) {
            return
        }
        List<MapEntryExpression> argumentMapEntryExpressionList = new ArrayList<>()
        if (methodArgumentsPresent(iMethodNode.getParameters())) {
            for (parameter in iMethodNode.getParameters()) {
                argumentMapEntryExpressionList.add(new MapEntryExpression(GeneralUtils.constX(parameter.getName()), GeneralUtils.varX(parameter.getName())))
            }
        }
        Statement engineDeclarationStatement = createEngineDeclaration()
        Statement automaticThisDeclaration = GeneralUtils.declS(
                iMethodNode.isStatic() ? GeneralUtils.varX("automaticThis", ClassHelper.make(Class.class)) : GeneralUtils.varX("automaticThis", iMethodNode.getDeclaringClass()),
                GeneralUtils.varX("this", iMethodNode.getDeclaringClass())
        )
        Statement firstStatement = checkSuperConstructorCall(iMethodNode)
        Statement methodExecutionOpen = new ExpressionStatement(
                GeneralUtils.callX(
                        GeneralUtils.varX(getEngineVarName()),
                        "methodExecutionOpen",
                        GeneralUtils.args(
                                GeneralUtils.ctorX(
                                        ClassHelper.make(MetaDataMethodNode.class),
                                        GeneralUtils.args(
                                                GeneralUtils.constX(iMethodNode.getDeclaringClass().getNameWithoutPackage()),
                                                GeneralUtils.constX(iMethodNode.getDeclaringClass().getPackageName()),
                                                GeneralUtils.constX(iMethodNode.getName()),
                                                GeneralUtils.constX(iMethodNode.getColumnNumber()),
                                                GeneralUtils.constX(iMethodNode.getLastColumnNumber()),
                                                GeneralUtils.constX(iMethodNode.getLineNumber()),
                                                GeneralUtils.constX(iMethodNode.getLastLineNumber())
                                        )
                                ),
                                new MapExpression(
                                        argumentMapEntryExpressionList
                                )
                        )
                )
        )
        Statement exceptionStatement = new ExpressionStatement(GeneralUtils.callX(GeneralUtils.varX(getEngineVarName()), "exception", GeneralUtils.args(GeneralUtils.varX("automaticException"))))
        if (carburetorLevel.value() >= CarburetorLevel.STATEMENT.value()) {
            iMethodNode.getCode().visit(new CarburetorVisitor(this, carburetorLevel))//<<<<<<<<<<<<<<VISIT<<<<<
            methodStatementLevelTransformation(iMethodNode, firstStatement, engineDeclarationStatement, automaticThisDeclaration, methodExecutionOpen, exceptionStatement)
        } else if (carburetorLevel.value() == CarburetorLevel.METHOD.value()) {
            methodStatementLevelTransformation(iMethodNode, firstStatement, engineDeclarationStatement, automaticThisDeclaration, methodExecutionOpen, exceptionStatement)
        } else if (carburetorLevel.value() == CarburetorLevel.ERROR.value()) {
            methodErrorLevelTransformation(iMethodNode, firstStatement, engineDeclarationStatement, automaticThisDeclaration, methodExecutionOpen)
        } else {
            throw new CarburetorCompileException(iMethodNode, "Unsupported Carburetor Level: " + carburetorLevel.toString(), this)
        }
    }

    void methodStatementLevelTransformation(MethodNode iMethodNode, Statement firstStatement, Statement engineDeclarationStatement, Statement automaticThisDeclaration, ExpressionStatement methodExecutionOpen, Statement exceptionStatement) {
        iMethodNode.setCode(
                GeneralUtils.block(
                        firstStatement,
                        engineDeclarationStatement,
                        automaticThisDeclaration,
                        methodExecutionOpen,
                        {
                            TryCatchStatement tryCatchStatement = new TryCatchStatement(
                                    {
                                        if (iMethodNode.isVoidMethod()) {
                                            return iMethodNode.getCode()
                                        } else {
                                            return new ExpressionStatement(GeneralUtils.callX(
                                                    GeneralUtils.varX(getEngineVarName()),
                                                    "executeMethod",
                                                    GeneralUtils.args(wrapStatementIntoClosure(iMethodNode.getCode()), GeneralUtils.varX("automaticThis"))
                                            ))
                                        }
                                    }.call() as Statement,
                                    new ExpressionStatement(GeneralUtils.callX(GeneralUtils.varX(getEngineVarName()), "executionClose"))
                            )
                            tryCatchStatement.addCatch(
                                    GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Exception.class), "automaticException"), GeneralUtils.block(exceptionStatement, createThrowStatement()))
                            )
                            return tryCatchStatement
                        }.call() as TryCatchStatement
                )
        )
    }

    void methodErrorLevelTransformation(MethodNode iMethodNode, Statement firstStatement, Statement engineDeclarationStatement, Statement automaticThisDeclaration, Statement methodExecutionOpen) {
        Statement logException = new ExpressionStatement(GeneralUtils.callX(GeneralUtils.varX(getEngineVarName()), "exception", GeneralUtils.args(GeneralUtils.varX("automaticException"))))
        iMethodNode.setCode(
                GeneralUtils.block(
                        firstStatement,
                        engineDeclarationStatement,
                        automaticThisDeclaration,
                        {
                            TryCatchStatement tryCatchStatement = new TryCatchStatement(iMethodNode.getCode(), EmptyStatement.INSTANCE)
                            tryCatchStatement.addCatch(
                                    GeneralUtils.catchS(
                                            GeneralUtils.param(ClassHelper.make(Exception.class), "automaticException"),
                                            GeneralUtils.block(
                                                    methodExecutionOpen,
                                                    logException,
                                                    new ExpressionStatement(GeneralUtils.callX(GeneralUtils.varX("automaticBlackBox"), "executionClose")),
                                                    createThrowStatement()
                                            )
                                    )
                            )
                            return tryCatchStatement
                        }.call() as TryCatchStatement
                )
        )
    }

    Statement createThrowStatement() {
        ThrowStatement throwStatement = GeneralUtils.throwS(GeneralUtils.varX("automaticException"))
        throwStatement.setSourcePosition(annotationNode)
        return throwStatement
    }

    Statement text2statement(String iCodeText) {
        List<ASTNode> resultingStatements = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS, true, iCodeText.replace("\$", "\\\$"))
        return resultingStatements.first() as Statement
    }

    String codeString(ASTNode iAstNode) {
        StringWriter stringWriter = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(stringWriter))
        return stringWriter.getBuffer().toString()
    }

    final Boolean methodArgumentsPresent(Object iArgs) {
        if (iArgs != null) {
            if (iArgs instanceof Collection) {
                return iArgs.size() > 0
            } else if (iArgs instanceof Object[]) {
                return iArgs.length > 0
            } else {
                return false
            }
        } else {
            return false
        }
    }

    BlockStatement transformControlStatement(Statement statement, String sourceNodeName) {
        BlockStatement blockStatement = GeneralUtils.block(new VariableScope())
        MethodCallExpression methodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "preprocessControlStatement",
                GeneralUtils.args(
                        GeneralUtils.ctorX(
                                ClassHelper.make(MetaDataStatement.class),
                                GeneralUtils.args(
                                        GeneralUtils.constX(statement.getClass().getSimpleName()),
                                        GeneralUtils.constX(statement.origCodeString),
                                        GeneralUtils.constX(statement.getColumnNumber()),
                                        GeneralUtils.constX(statement.getLastColumnNumber()),
                                        GeneralUtils.constX(statement.getLineNumber()),
                                        GeneralUtils.constX(statement.getLastLineNumber()),
                                        GeneralUtils.constX(sourceNodeName)
                                )
                        )
                )
        )
        blockStatement.addStatement(new ExpressionStatement(methodCallExpression))
        blockStatement.addStatement(statement)
        blockStatement.copyNodeMetaData(statement)
        blockStatement.setSourcePosition(statement)
        blockStatement.transformedBy = this
        return blockStatement
    }

    Statement transformStatement(Statement statement, String sourceNodeName) {
        if (statement == null || statement instanceof EmptyStatement || statement.transformedBy == this) {
            return statement
        }
        if (carburetorLevel.value() < CarburetorLevel.STATEMENT.value() || statement instanceof BlockStatement || statement instanceof ExpressionStatement) {
            return statement
        }
        if (statement instanceof ReturnStatement || statement instanceof ContinueStatement || statement instanceof BreakStatement || statement instanceof ThrowStatement) {
            return transformControlStatement(statement, sourceNodeName)
        }
        BlockStatement blockStatement = GeneralUtils.block(new VariableScope())
        blockStatement.addStatement(new ExpressionStatement(GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "statementExecutionOpen",
                GeneralUtils.args(
                        GeneralUtils.ctorX(
                                ClassHelper.make(MetaDataStatement.class),
                                GeneralUtils.args(
                                        GeneralUtils.constX(statement.getClass().getSimpleName()),
                                        GeneralUtils.constX(statement.origCodeString),
                                        GeneralUtils.constX(statement.getColumnNumber()),
                                        GeneralUtils.constX(statement.getLastColumnNumber()),
                                        GeneralUtils.constX(statement.getLineNumber()),
                                        GeneralUtils.constX(statement.getLastLineNumber()),
                                        GeneralUtils.constX(sourceNodeName)
                                )
                        )
                )
        )))
        blockStatement.addStatement(statement)
        blockStatement.addStatement(text2statement("${getEngineVarName()}.executionClose()"))
        blockStatement.copyNodeMetaData(statement)
        blockStatement.setSourcePosition(statement)
        blockStatement.transformedBy = this
        return blockStatement
    }

    ListOfExpressionsExpression transformDeclarationExpression(DeclarationExpression declarationExpression, String sourceNodeName) {
        ListOfExpressionsExpression listOfExpressionsExpression = new ListOfExpressionsExpression()
        MethodCallExpression expressionExecutionOpenMethodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "expressionExecutionOpen",
                GeneralUtils.args(
                        GeneralUtils.ctorX(
                                ClassHelper.make(MetaDataExpression.class),
                                GeneralUtils.args(
                                        GeneralUtils.constX(declarationExpression.getClass().getSimpleName()),
                                        GeneralUtils.constX(declarationExpression.origCodeString),
                                        GeneralUtils.constX(declarationExpression.getColumnNumber()),
                                        GeneralUtils.constX(declarationExpression.getLastColumnNumber()),
                                        GeneralUtils.constX(declarationExpression.getLineNumber()),
                                        GeneralUtils.constX(declarationExpression.getLastLineNumber()),
                                        GeneralUtils.constX(sourceNodeName)
                                )
                        )
                )
        )
        MethodCallExpression expressionExecutionCloseMethodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "executionClose"
        )
        listOfExpressionsExpression.addExpression(expressionExecutionOpenMethodCallExpression)
        listOfExpressionsExpression.addExpression(declarationExpression)
        listOfExpressionsExpression.addExpression(expressionExecutionCloseMethodCallExpression)
        listOfExpressionsExpression.copyNodeMetaData(declarationExpression)
        listOfExpressionsExpression.setSourcePosition(declarationExpression)
        listOfExpressionsExpression.transformedBy = this
        return listOfExpressionsExpression
    }

    Expression transformExpression(Expression expression, String sourceNodeName) {
        Expression transformedExpression = expression
        if (expression == null ||
                carburetorLevel.value() < CarburetorLevel.EXPRESSION.value() ||
                expression instanceof EmptyExpression ||
                expression instanceof MapEntryExpression ||
                expression instanceof ArgumentListExpression ||
                (expression instanceof ConstructorCallExpression && expression.isSpecialCall()) ||
                (expression instanceof VariableExpression && expression.isSuperExpression()) ||
                (expression.transformedBy == this)
        ) {
            expression?.transformedBy == true
            return expression
        } else if (expression.getClass() == DeclarationExpression.class) {
            return transformDeclarationExpression(expression as DeclarationExpression, sourceNodeName)
        } else if (expression.getClass() == BitwiseNegationExpression.class) {
            transformedExpression = new BitwiseNegationExpression(transformExpression(expression.getExpression() as Expression, "BitwiseNegationExpression:expression"))
        } else if (expression.getClass() == NotExpression.class) {
            transformedExpression = new NotExpression(transformExpression(expression.getExpression() as Expression, "NotExpression:expression"))
        } else if (expression.getClass() == BooleanExpression.class) {
            transformedExpression = new BooleanExpression(transformExpression(expression.getExpression() as Expression, "BooleanExpression:expression"))
        } else if (expression.getClass() == CastExpression.class) {
            transformedExpression = new CastExpression(expression.getType(), transformExpression(expression.getExpression() as Expression, "ClassExpression:expression"))
        } else if (expression.getClass() == ConstructorCallExpression.class) {
            if (!(expression.getArguments().getClass() == TupleExpression.class)) {
                transformedExpression = new ConstructorCallExpression(expression.getType(), transformExpression(expression.getArguments() as Expression, "ConstructorCallExpression:arguments"))
                transformedExpression.usingAnonymousInnerClass = expression.usingAnonymousInnerClass
            }
        } else if (expression.getClass() == MethodPointerExpression.class) {
            transformedExpression = new MethodPointerExpression(transformExpression(expression.getExpression() as Expression, "MethodPointerExpression:expression"),
                    transformExpression(expression.getMethodName() as Expression, "MethodPointerExpression:methodName"))
        } else if (expression.getClass() == AttributeExpression.class) {
            transformedExpression = new AttributeExpression(transformExpression(expression.getObjectExpression() as Expression, "AttributeExpression:objectExpression"),
                    transformExpression(expression.getProperty() as Expression, "PropertyExpression:property"))
        } else if (expression.getClass() == PropertyExpression.class) {
            if (!(expression.getObjectExpression() instanceof ClassExpression && expression.getProperty() instanceof ConstantExpression && expression.getProperty().getValue().toString() == "this")) {
                transformedExpression = new PropertyExpression(transformExpression(expression.getObjectExpression() as Expression, "PropertyExpression:objectExpression"),
                        transformExpression(expression.getProperty() as Expression, "PropertyExpression:property"))
            }
        } else if (expression.getClass() == RangeExpression.class) {
            transformedExpression = new RangeExpression(transformExpression(expression.getFrom() as Expression, "RangeExpression:from"),
                    transformExpression(expression.getTo() as Expression, "RangeExpression:to"), expression.isInclusive() as Boolean)
        } else if (expression.getClass() == SpreadExpression.class) {
            transformedExpression = new SpreadExpression(transformExpression(expression.getExpression() as Expression, "SpreadExpression:expression"))
        } else if (expression.getClass() == SpreadMapExpression.class) {
            transformedExpression = new SpreadMapExpression(transformExpression(expression.getExpression() as Expression, "SpreadExpression:expression"))
        } else if (expression.getClass() == StaticMethodCallExpression.class) {
            //todo: StaticMethodCall transformation (wrap into "executeStaticMethod"; same applies to methodCallExpression when objectExpression is ClassExpression)
            transformedExpression = new StaticMethodCallExpression(expression.getOwnerType() as ClassNode, expression.getMethod() as String,
                    transformExpression(expression.getArguments() as Expression, "StaticMethodCallExpression:arguments"))
        } else if (expression.getClass() == ElvisOperatorExpression.class) {
            transformedExpression = new ElvisOperatorExpression(
                    transformExpression(expression.getTrueExpression() as Expression, "ElvisOperatorExpression:trueExpression"),
                    transformExpression(expression.getFalseExpression() as Expression, "ElvisOperatorExpression:falseExpression")
            )
        } else if (expression.getClass() == TernaryExpression.class) {
            transformedExpression = new TernaryExpression(
                    new BooleanExpression(transformExpression(expression.getBooleanExpression() as Expression, "TernaryExpression:booleanExpression")),
                    transformExpression(expression.getTrueExpression() as Expression, "TernaryExpression:trueExpression"),
                    transformExpression(expression.getFalseExpression() as Expression, "TernaryExpression:falseExpression")
            )
        } else if (expression.getClass() == UnaryMinusExpression.class) {
            transformedExpression = new UnaryMinusExpression(transformExpression(expression.getExpression() as Expression, "UnaryMinusExpression:expression"))
        } else if (expression.getClass() == UnaryPlusExpression.class) {
            transformedExpression = new UnaryPlusExpression(transformExpression(expression.getExpression() as Expression, "UnaryPlusExpression:expression"))
        }
        transformedExpression.transformedBy = this
        transformedExpression.copyNodeMetaData(expression)
        transformedExpression.setSourcePosition(expression)
        return wrapExpressionIntoMethodCallExpression(transformedExpression, sourceNodeName)
    }

}
