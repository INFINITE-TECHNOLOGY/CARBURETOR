package io.infinite.carburetor

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileDynamic
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.supplies.ast.cache.CacheFieldInit
import io.infinite.supplies.ast.exceptions.CompileException
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement
import io.infinite.supplies.ast.other.ASTUtils
import jdk.internal.org.objectweb.asm.Opcodes
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.classgen.VariableScopeVisitor
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression
import org.slf4j.MDC

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
@Slf4j
@CompileDynamic
abstract class CarburetorTransformation extends AbstractASTTransformation {

    AnnotationNode annotatationNode
    CarburetorLevel carburetorLevel
    MethodNode methodNode
    private static Integer uniqueClosureParamCounter = 0
    static String lastCode
    ASTUtils astUtils = new ASTUtils()
    static private final Object compilationLock = new Object()

    @CacheFieldInit
    CarburetorConfig carburetorConfig = initCarburetorConfig()

    static {
        ASTNode.getMetaClass().origCodeString = null
        ASTNode.getMetaClass().transformedBy = null
    }

    abstract String getEngineVarName()

    abstract Boolean excludeMethodNode(MethodNode methodNode)

    abstract void optionalDeclarations(ClassNode classNode)

    abstract Class getEngineFactoryClass()

    abstract Expression getEngineInitArgs()

    abstract void methodDeclarations(MethodNode methodNode)

    abstract void getAnnotationParameters()

    // MAIN ENTRY POINT \/\/\/\/\/\/\/\/
    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        synchronized (compilationLock) {
            try {
                init(iAstNodeArray, iSourceUnit)
                if (iAstNodeArray[1] instanceof MethodNode) {
                    AnnotationNode methodAnnotationNode = iAstNodeArray[0] as AnnotationNode
                    visitMethod(iAstNodeArray[1] as MethodNode, methodAnnotationNode)
                } else if (iAstNodeArray[1] instanceof ClassNode) {
                    AnnotationNode classAnnotationNode = iAstNodeArray[0] as AnnotationNode
                    visitClassNode(iAstNodeArray[1] as ClassNode, classAnnotationNode)
                } else {
                    throw new CompileException(iAstNodeArray[1], "Unsupported Annotated Node; Only [Class, Method, Constructor] are supported.")
                }
            } catch (Exception exception) {
                log.error(exception.getMessage(), exception)
                throw new CompileException(iAstNodeArray[1], exception)
            }
        }
    }
    // MAIN ENTRY POINT /\/\/\/\/\/\/\/\

    void visitClassNode(ClassNode classNode, AnnotationNode classAnnotationNode) {
        classNode.methods.each {
            visitMethod(it, it.getAnnotations(classAnnotationNode.getClassNode())[0] ?: classAnnotationNode)
        }
        classNode.declaredConstructors.each {
            visitMethod(it, it.getAnnotations(classAnnotationNode.getClassNode())[0] ?: classAnnotationNode)
        }
    }

    void visitMethod(MethodNode methodNode, AnnotationNode methodAnnotationNode) {
        if (methodNode.isAbstract() || excludeMethodNode(methodNode)) {
            return
        }
        uniqueClosureParamCounter = 0
        this.annotatationNode = methodAnnotationNode
        this.methodNode = methodNode
        try {
            if (methodNode.transformedBy == this) {
                return
            }
            if (methodNode.getDeclaringClass().getOuterClass() != null) {
                throw new CompileException(methodNode, "Carburetor currently does not support annotations in Inner Classes.")
            }
            if (codeString(methodNode.getCode()).contains(getEngineVarName())) {
                throw new CompileException(methodNode, "Duplicate transformation")
            }
            mandatoryClassDeclarations(methodNode.getDeclaringClass())
            methodDeclarations(methodNode)
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            MDC.put("unitName", "CARBURETOR_$className.${methodName.replace("<", "").replace(">", "")}")
            carburetorLevel = getAnnotationParameter("level", carburetorConfig.getLevel(methodAnnotationNode.getClassNode().getName()), methodAnnotationNode) as CarburetorLevel
            getAnnotationParameters()
            transformMethod(methodNode)
            sourceUnit.AST.classes.each {
                new VariableScopeVisitor(sourceUnit, true).visitClass(it)
            }
            //new VariableScopeVisitor(sourceUnit, true).visitClass(methodNode.declaringClass)
            lastCode = codeString(methodNode.getCode())
            log.debug(lastCode)
            methodNode.transformedBy = this
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception)
            throw new CompileException(methodNode, exception)
        }
    }

    void mandatoryClassDeclarations(ClassNode classNode) {
        optionalDeclarations(classNode)
        ClassNode classNodeType = classNode.getPlainNodeReference()
        //walkaround A transform used a generics containing ClassNode NamedArgumentConstructorClass for the field thisInstance directly....
        if (classNode.getDeclaredField(getThisInstanceVarName()) == null) {
            classNode.addField(getThisInstanceVarName(),
                    Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_PUBLIC,//ACC_PUBLIC to workaround MissingPropertyException SpringBootApp$$EnhancerBySpringCGLIB$$<..>
                    classNodeType,
                    GeneralUtils.varX("this", classNodeType)
            )
        }
        if (classNode.getDeclaredField(getThisClassVarName()) == null) {
            classNode.addField(getThisClassVarName(),
                    Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,//ACC_PUBLIC to workaround MissingPropertyException SpringBootApp$$EnhancerBySpringCGLIB$$<..>
                    ClassHelper.make(Class.class).getPlainNodeReference(), //same walkaround as above
                    GeneralUtils.varX("this", classNodeType)
            )
        }
        if (classNode.getDeclaredField(getEngineVarName()) == null) {
            classNode.addField(getEngineVarName(),
                    Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,//ACC_PUBLIC to workaround MissingPropertyException SpringBootApp$$EnhancerBySpringCGLIB$$<..>
                    ClassHelper.make(CarburetorEngine.class),
                    GeneralUtils.callX(
                            GeneralUtils.ctorX(ClassHelper.make(getEngineFactoryClass())),
                            getEngineFactoryMethodName(),
                            getEngineInitArgs()
                    )
            )
        }
    }

    String getThisVarNameBasedOnContext() {
        if (methodNode.isStatic()) {
            return getThisClassVarName()
        } else {
            return getThisInstanceVarName()
        }
    }

    String getThisInstanceVarName() {
        "thisInstance"
    }

    String getThisClassVarName() {
        "thisClass"
    }

    String getMethodEndName() {
        "methodEnd"
    }

    String getEngineFactoryMethodName() {
        "getInstance"
    }

    Object getAnnotationParameter(String annotationName, Object defaultValue, AnnotationNode annotationNode) {
        Object result
        Expression memberExpression = annotationNode.getMember(annotationName)
        log.debug(annotationNode.getClassNode().getName() + ":" + annotationNode.getLineNumber())
        if (memberExpression instanceof PropertyExpression) {
            log.debug("PropertyExpression")
            ConstantExpression constantExpression = memberExpression.getProperty() as ConstantExpression
            result = constantExpression.getValue()
        } else if (memberExpression instanceof ConstantExpression) {
            log.debug("ConstantExpression")
            result = memberExpression.getValue()
        } else if (memberExpression == null) {
            log.debug("defaultValue")
            result = defaultValue
        } else {
            throw new CompileException(memberExpression, "Unsupported annotation \"$annotationName\" member expression class: " + memberExpression.getClass().getCanonicalName() + " for method " + MDC.get("unitName"))
        }
        log.debug(annotationName + "=" + result)
        return result
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

    MethodCallExpression wrapExpressionIntoMethodCallExpression(Expression expression, String sourceNodeName) {
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
                                        GeneralUtils.constX(expression.getLineNumber()),
                                        GeneralUtils.constX(expression.getLastLineNumber()),
                                        GeneralUtils.constX(expression.getColumnNumber()),
                                        GeneralUtils.constX(expression.getLastColumnNumber()),
                                        GeneralUtils.constX(methodNode.getName()),
                                        GeneralUtils.constX(methodNode.getDeclaringClass().getName())
                                )
                        ),
                        closureExpression,
                        GeneralUtils.varX(getThisVarNameBasedOnContext())
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

    void transformMethod(MethodNode iMethodNode) {
        if (carburetorLevel.value() == CarburetorLevel.NONE.value()) {
            return
        }
        List<MapEntryExpression> argumentMapEntryExpressionList = new ArrayList<>()
        if (astUtils.methodArgumentsPresent(iMethodNode.getParameters())) {
            for (parameter in iMethodNode.getParameters()) {
                argumentMapEntryExpressionList.add(new MapEntryExpression(GeneralUtils.constX(parameter.getName()), GeneralUtils.varX(parameter.getName())))
            }
        }
        Statement firstStatement = checkSuperConstructorCall(iMethodNode)
        Statement methodExecutionOpen = createMethodLogStatement("methodBegin", iMethodNode, argumentMapEntryExpressionList)
        Statement methodException = createMethodLogStatement("methodException", iMethodNode, argumentMapEntryExpressionList, GeneralUtils.varX("automaticException"))
        if (carburetorLevel.value() >= CarburetorLevel.STATEMENT.value()) {
            iMethodNode.getCode().visit(new CarburetorVisitor(this, carburetorLevel))//<<<<<<<<<<<<<<VISIT<<<<<
            methodStatementLevelTransformation(iMethodNode, firstStatement, methodExecutionOpen, methodException)
        } else if (carburetorLevel.value() == CarburetorLevel.METHOD.value()) {
            methodStatementLevelTransformation(iMethodNode, firstStatement, methodExecutionOpen, methodException)
        } else if (carburetorLevel.value() == CarburetorLevel.ERROR.value()) {
            methodErrorLevelTransformation(iMethodNode, firstStatement, methodException)
        } else {
            throw new CompileException(iMethodNode, "Unsupported Carburetor Level: " + carburetorLevel.toString())
        }
    }

    ExpressionStatement createMethodLogStatement(String methodLogName, MethodNode methodNode, ArrayList<MapEntryExpression> argumentMapEntryExpressionList, Expression... additionalArgs) {
        def args = GeneralUtils.args(
                metaDataMethodNode(methodNode),
                new MapExpression(
                        argumentMapEntryExpressionList
                )
        )
        if (astUtils.methodArgumentsPresent(additionalArgs)) {
            additionalArgs.each {
                args.addExpression(it)
            }
        }
        return new ExpressionStatement(
                GeneralUtils.callX(
                        GeneralUtils.varX(getEngineVarName()),
                        methodLogName,
                        args
                )
        )
    }

    ConstructorCallExpression metaDataMethodNode(MethodNode methodNode) {
        return GeneralUtils.ctorX(
                ClassHelper.make(MetaDataMethodNode.class),
                GeneralUtils.args(
                        GeneralUtils.constX(methodNode.getLineNumber()),
                        GeneralUtils.constX(methodNode.getLastLineNumber()),
                        GeneralUtils.constX(methodNode.getColumnNumber()),
                        GeneralUtils.constX(methodNode.getLastColumnNumber()),
                        GeneralUtils.constX(methodNode.getName()),
                        GeneralUtils.constX(methodNode.getDeclaringClass().getName())
                )
        )
    }

    void methodStatementLevelTransformation(MethodNode iMethodNode, Statement firstStatement, ExpressionStatement methodExecutionOpen, Statement methodExecutionOpenException) {
        iMethodNode.setCode(
                GeneralUtils.block(
                        firstStatement,
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
                                                    GeneralUtils.args(wrapStatementIntoClosure(iMethodNode.getCode()), GeneralUtils.varX(getThisVarNameBasedOnContext()))
                                            ))
                                        }
                                    }.call() as Statement,
                                    new ExpressionStatement(
                                            GeneralUtils.callX(
                                                    GeneralUtils.varX(getEngineVarName()),
                                                    getMethodEndName(),
                                                    GeneralUtils.args(metaDataMethodNode(methodNode))
                                            )
                                    )
                            )
                            tryCatchStatement.addCatch(
                                    GeneralUtils.catchS(
                                            GeneralUtils.param(ClassHelper.make(Exception.class), "automaticException"),
                                            GeneralUtils.block(
                                                    methodExecutionOpenException,
                                                    createThrowStatement()
                                            )
                                    )
                            )
                            return tryCatchStatement
                        }.call() as TryCatchStatement
                )
        )
    }

    void methodErrorLevelTransformation(MethodNode iMethodNode, Statement firstStatement, Statement methodException) {
        iMethodNode.setCode(
                GeneralUtils.block(
                        firstStatement,
                        {
                            TryCatchStatement tryCatchStatement = new TryCatchStatement(iMethodNode.getCode(), EmptyStatement.INSTANCE)
                            tryCatchStatement.addCatch(
                                    GeneralUtils.catchS(
                                            GeneralUtils.param(ClassHelper.make(Exception.class), "automaticException"),
                                            GeneralUtils.block(
                                                    methodException,
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
        throwStatement.setSourcePosition(annotatationNode)
        return throwStatement
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
                                        GeneralUtils.constX(statement.getLineNumber()),
                                        GeneralUtils.constX(statement.getLastLineNumber()),
                                        GeneralUtils.constX(statement.getColumnNumber()),
                                        GeneralUtils.constX(statement.getLastColumnNumber()),
                                        GeneralUtils.constX(methodNode.getName()),
                                        GeneralUtils.constX(methodNode.getDeclaringClass().getName())
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
                "statementBegin",
                GeneralUtils.args(
                        metaDataStatement(statement)
                )
        )))
        blockStatement.addStatement(statement)
        blockStatement.addStatement(new ExpressionStatement(GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "statementEnd",
                GeneralUtils.args(
                        metaDataStatement(statement)
                )
        )))
        blockStatement.copyNodeMetaData(statement)
        blockStatement.setSourcePosition(statement)
        blockStatement.transformedBy = this
        return blockStatement
    }

    ConstructorCallExpression metaDataStatement(Statement statement) {
        return GeneralUtils.ctorX(
                ClassHelper.make(MetaDataStatement.class),
                GeneralUtils.args(
                        GeneralUtils.constX(statement.getClass().getSimpleName()),
                        GeneralUtils.constX(statement.getLineNumber()),
                        GeneralUtils.constX(statement.getLastLineNumber()),
                        GeneralUtils.constX(statement.getColumnNumber()),
                        GeneralUtils.constX(statement.getLastColumnNumber()),
                        GeneralUtils.constX(methodNode.getName()),
                        GeneralUtils.constX(methodNode.getDeclaringClass().getName())
                )
        )
    }

    ListOfExpressionsExpression transformDeclarationExpression(DeclarationExpression declarationExpression, String sourceNodeName) {
        ListOfExpressionsExpression listOfExpressionsExpression = new ListOfExpressionsExpression()
        MethodCallExpression expressionStart = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "expressionBegin",
                GeneralUtils.args(
                        metaDataExpression(declarationExpression)
                )
        )
        MethodCallExpression expressionEnd = GeneralUtils.callX(
                GeneralUtils.varX(getEngineVarName()),
                "expressionEnd",
                GeneralUtils.args(
                        metaDataExpression(declarationExpression)
                )
        )
        listOfExpressionsExpression.addExpression(expressionStart)
        listOfExpressionsExpression.addExpression(declarationExpression)
        listOfExpressionsExpression.addExpression(expressionEnd)
        listOfExpressionsExpression.copyNodeMetaData(declarationExpression)
        listOfExpressionsExpression.setSourcePosition(declarationExpression)
        listOfExpressionsExpression.transformedBy = this
        listOfExpressionsExpression.origCodeString = declarationExpression.origCodeString
        return listOfExpressionsExpression
    }

    ConstructorCallExpression metaDataExpression(Expression expression) {
        GeneralUtils.ctorX(
                ClassHelper.make(MetaDataExpression.class),
                GeneralUtils.args(
                        GeneralUtils.constX(expression.getClass().getSimpleName()),
                        GeneralUtils.constX(expression.origCodeString),
                        GeneralUtils.constX(expression.getLineNumber()),
                        GeneralUtils.constX(expression.getLastLineNumber()),
                        GeneralUtils.constX(expression.getColumnNumber()),
                        GeneralUtils.constX(expression.getLastColumnNumber()),
                        GeneralUtils.constX(methodNode.getName()),
                        GeneralUtils.constX(methodNode.getDeclaringClass().getName())
                )
        )
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
                expression.transformedBy == this ||
                (expression.origCodeString == null || expression.origCodeString == "")
                ) {
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
                        transformExpression(expression.getProperty() as Expression, "PropertyExpression:property"), true)
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
        transformedExpression.origCodeString = expression.origCodeString
        transformedExpression.setSourcePosition(expression)
        return wrapExpressionIntoMethodCallExpression(transformedExpression, sourceNodeName)
    }

    // Utils \/\/\/\/\/\/
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

    Statement text2statement(String iCodeText) {
        List<ASTNode> resultingStatements = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS, true, iCodeText.replace("\$", "\\\$"))
        return resultingStatements.first() as Statement
    }

    String codeString(ASTNode astNode) {
        if (astNode.origCodeString != null && astNode.origCodeString != "") {
            return astNode.origCodeString
        } else {
            String codeString = astUtils.codeString(astNode)
            if (codeString == null || codeString == "") {
                throw new CompileException(astNode, "Null code string")
            }
            return codeString
        }
    }

    void report(String msg) {
        log.info("Carburator: " + Thread.currentThread().getName() + ": " + msg)
    }

}
