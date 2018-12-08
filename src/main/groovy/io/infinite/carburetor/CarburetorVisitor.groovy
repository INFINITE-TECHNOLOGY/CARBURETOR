package io.infinite.carburetor

import groovy.transform.ToString
import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.VariableScope
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils

@ToString(includeNames = true, includeFields = true)
class CarburetorVisitor extends CodeVisitorSupport {

    CarburetorTransformation carburetorTransformation
    CarburetorLevel carburetorLevel

    CarburetorVisitor(CarburetorTransformation carburetorTransformation, CarburetorLevel carburetorLevel) {
        this.carburetorTransformation = carburetorTransformation
        this.carburetorLevel = carburetorLevel
    }

    void transformStatementList(List<Statement> iStatementListToTransform, String iSourceNodeName) {
        List<Statement> transformedStatementList = iStatementListToTransform.getClass().newInstance() as List<Statement>
        for (Statement statementToTransform : iStatementListToTransform) {
            transformedStatementList.add(carburetorTransformation.transformStatement(statementToTransform, iSourceNodeName))
        }
        iStatementListToTransform.clear()
        iStatementListToTransform.addAll(transformedStatementList)
    }

    void transformExpressionList(List<Expression> iExpressionListToTransform, String iSourceNodeName) {
        List<Expression> transformedExpressionList = iExpressionListToTransform.getClass().newInstance() as List<Expression>
        for (Expression expressionToTransform : iExpressionListToTransform) {
            transformedExpressionList.addAll(carburetorTransformation.transformExpression(expressionToTransform, iSourceNodeName))
        }
        iExpressionListToTransform.clear()
        iExpressionListToTransform.addAll(transformedExpressionList)
    }

    @Override
    void visitBlockStatement(BlockStatement iBlockStatement) {
        iBlockStatement.origCodeString = carburetorTransformation.codeString(iBlockStatement)
        super.visitBlockStatement(iBlockStatement)
        transformStatementList(iBlockStatement.getStatements(), iBlockStatement.getClass().getSimpleName() + ":statements")
    }

    @Override
    void visitForLoop(ForStatement iForStatement) {
        iForStatement.origCodeString = carburetorTransformation.codeString(iForStatement)
        super.visitForLoop(iForStatement)
        iForStatement.setCollectionExpression(carburetorTransformation.transformExpression(iForStatement.getCollectionExpression(), iForStatement.getClass().getSimpleName() + ":collectionExpression"))
        iForStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iForStatement.getLoopBlock(), iForStatement.getClass().getSimpleName() + ":loopBlock")))
    }

    @Override
    void visitWhileLoop(WhileStatement iWhileStatement) {
        iWhileStatement.origCodeString = carburetorTransformation.codeString(iWhileStatement)
        super.visitWhileLoop(iWhileStatement)
        iWhileStatement.setBooleanExpression(new BooleanExpression(carburetorTransformation.transformExpression(iWhileStatement.getBooleanExpression(), iWhileStatement.getClass().getSimpleName() + ":booleanExpression")))
        iWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iWhileStatement.getLoopBlock(), iWhileStatement.getClass().getSimpleName() + ":loopBlock")))
    }

    @Override
    void visitDoWhileLoop(DoWhileStatement iDoWhileStatement) {
        iDoWhileStatement.origCodeString = carburetorTransformation.codeString(iDoWhileStatement)
        super.visitDoWhileLoop(iDoWhileStatement)
        iDoWhileStatement.setBooleanExpression(new BooleanExpression(carburetorTransformation.transformExpression(iDoWhileStatement.getBooleanExpression(), iDoWhileStatement.getClass().getSimpleName() + ":booleanExpression")))
        iDoWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iDoWhileStatement.getLoopBlock(), iDoWhileStatement.getClass().getSimpleName() + ":loopBlock")))
    }

    @Override
    void visitIfElse(IfStatement iIfStatement) {
        iIfStatement.origCodeString = carburetorTransformation.codeString(iIfStatement)
        super.visitIfElse(iIfStatement)
        iIfStatement.setBooleanExpression(new BooleanExpression(carburetorTransformation.transformExpression(iIfStatement.getBooleanExpression(), iIfStatement.getClass().getSimpleName() + ":booleanExpression")))
        iIfStatement.setIfBlock(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iIfStatement.getIfBlock(), iIfStatement.getClass().getSimpleName() + ":ifBlock")))
        iIfStatement.setElseBlock(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iIfStatement.getElseBlock(), iIfStatement.getClass().getSimpleName() + ":elseBlock")))
    }

    @Override
    void visitExpressionStatement(ExpressionStatement iExpressionStatement) {
        iExpressionStatement.origCodeString = carburetorTransformation.codeString(iExpressionStatement)
        super.visitExpressionStatement(iExpressionStatement)
        iExpressionStatement.setExpression(carburetorTransformation.transformExpression(iExpressionStatement.getExpression(), iExpressionStatement.getClass().getSimpleName() + ":expression"))
    }

    @Override
    void visitReturnStatement(ReturnStatement iReturnStatement) {
        iReturnStatement.origCodeString = carburetorTransformation.codeString(iReturnStatement)
        super.visitReturnStatement(iReturnStatement)
        if (!iReturnStatement.isReturningNullOrVoid()) {
            iReturnStatement.setExpression(carburetorTransformation.transformExpression(iReturnStatement.getExpression(), iReturnStatement.getClass().getSimpleName() + ":expression"))
        }
    }

    @Override
    void visitAssertStatement(AssertStatement iAssertStatement) {
        iAssertStatement.origCodeString = carburetorTransformation.codeString(iAssertStatement)
        super.visitAssertStatement(iAssertStatement)
        iAssertStatement.setBooleanExpression(new BooleanExpression(carburetorTransformation.transformExpression(iAssertStatement.getBooleanExpression(), iAssertStatement.getClass().getSimpleName() + ":booleanExpression")))
        iAssertStatement.setMessageExpression(carburetorTransformation.transformExpression(iAssertStatement.getMessageExpression(), iAssertStatement.getClass().getSimpleName() + ":messageExpression"))
    }

    @Override
    void visitTryCatchFinally(TryCatchStatement iTryCatchStatement) {
        iTryCatchStatement.origCodeString = carburetorTransformation.codeString(iTryCatchStatement)
        super.visitTryCatchFinally(iTryCatchStatement)
        iTryCatchStatement.setTryStatement(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iTryCatchStatement.getTryStatement(), iTryCatchStatement.getClass().getSimpleName() + ":tryStatement")))
        for (CatchStatement catchStatement : iTryCatchStatement.getCatchStatements()) {
            catchStatement.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(catchStatement.getCode(), catchStatement.getClass().getSimpleName() + ":code")))
        }
        iTryCatchStatement.setFinallyStatement(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iTryCatchStatement.getFinallyStatement(), iTryCatchStatement.getClass().getSimpleName() + ":finallyStatement")))
    }

    @Override
    protected void visitEmptyStatement(EmptyStatement iEmptyStatement) {
        iEmptyStatement.origCodeString = carburetorTransformation.codeString(iEmptyStatement)
        super.visitEmptyStatement(iEmptyStatement)

    }

    @Override
    void visitSwitch(SwitchStatement iSwitchStatement) {
        iSwitchStatement.origCodeString = carburetorTransformation.codeString(iSwitchStatement)
        super.visitSwitch(iSwitchStatement)
        iSwitchStatement.setExpression(carburetorTransformation.transformExpression(iSwitchStatement.getExpression(), iSwitchStatement.getClass().getSimpleName() + ":expression"))
        for (CaseStatement caseStatement : iSwitchStatement.getCaseStatements()) {
            caseStatement.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(caseStatement.getCode(), caseStatement.getClass().getSimpleName() + ":code")))
        }
        iSwitchStatement.setDefaultStatement(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iSwitchStatement.getDefaultStatement(), iSwitchStatement.getClass().getSimpleName() + ":defaultStatement")))
    }

    @Override
    void visitCaseStatement(CaseStatement iCaseStatement) {
        iCaseStatement.origCodeString = carburetorTransformation.codeString(iCaseStatement)
        super.visitCaseStatement(iCaseStatement)
        iCaseStatement.setExpression(carburetorTransformation.transformExpression(iCaseStatement.getExpression(), iCaseStatement.getClass().getSimpleName() + ":expression"))
        iCaseStatement.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iCaseStatement.getCode(), iCaseStatement.getClass().getSimpleName() + ":code")))
    }

    @Override
    void visitBreakStatement(BreakStatement iBreakStatement) {
        iBreakStatement.origCodeString = carburetorTransformation.codeString(iBreakStatement)
        super.visitBreakStatement(iBreakStatement)
    }

    @Override
    void visitContinueStatement(ContinueStatement iContinueStatement) {
        iContinueStatement.origCodeString = carburetorTransformation.codeString(iContinueStatement)
        super.visitContinueStatement(iContinueStatement)

    }

    @Override
    void visitSynchronizedStatement(SynchronizedStatement iSynchronizedStatement) {
        iSynchronizedStatement.origCodeString = carburetorTransformation.codeString(iSynchronizedStatement)
        super.visitSynchronizedStatement(iSynchronizedStatement)
        iSynchronizedStatement.setExpression(carburetorTransformation.transformExpression(iSynchronizedStatement.getExpression(), iSynchronizedStatement.getClass().getSimpleName() + ":expression"))
        iSynchronizedStatement.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iSynchronizedStatement.getCode(), iSynchronizedStatement.getClass().getSimpleName() + ":code")))
    }

    @Override
    void visitThrowStatement(ThrowStatement iThrowStatement) {
        iThrowStatement.origCodeString = carburetorTransformation.codeString(iThrowStatement)
        super.visitThrowStatement(iThrowStatement)
        iThrowStatement.setExpression(carburetorTransformation.transformExpression(iThrowStatement.getExpression(), iThrowStatement.getClass().getSimpleName() + ":expression"))
    }

    @Override
    void visitMethodCallExpression(MethodCallExpression iMethodCallExpression) {
        iMethodCallExpression.origCodeString = carburetorTransformation.codeString(iMethodCallExpression)
        super.visitMethodCallExpression(iMethodCallExpression)
        iMethodCallExpression.setObjectExpression(carburetorTransformation.transformExpression(iMethodCallExpression.getObjectExpression(), iMethodCallExpression.getClass().getSimpleName() + ":objectExpression"))
        iMethodCallExpression.setArguments(carburetorTransformation.transformExpression(iMethodCallExpression.getArguments(), iMethodCallExpression.getClass().getSimpleName() + ":arguments"))
    }

    @Override
    void visitStaticMethodCallExpression(StaticMethodCallExpression iStaticMethodCallExpression) {
        iStaticMethodCallExpression.origCodeString = carburetorTransformation.codeString(iStaticMethodCallExpression)
        super.visitStaticMethodCallExpression(iStaticMethodCallExpression)
    }

    @Override
    void visitConstructorCallExpression(ConstructorCallExpression iConstructorCallExpression) {
        iConstructorCallExpression.origCodeString = carburetorTransformation.codeString(iConstructorCallExpression)
        super.visitConstructorCallExpression(iConstructorCallExpression)
    }

    @Override
    void visitBinaryExpression(BinaryExpression iBinaryExpression) {
        iBinaryExpression.origCodeString = carburetorTransformation.codeString(iBinaryExpression)
        iBinaryExpression.getRightExpression().visit(this)
        if (iBinaryExpression.operation.text != "=") {
            iBinaryExpression.getLeftExpression().visit(this)
            iBinaryExpression.setLeftExpression(carburetorTransformation.transformExpression(iBinaryExpression.getLeftExpression() as Expression, iBinaryExpression.getClass().getSimpleName() + ":leftExpression"))
        }
        iBinaryExpression.setRightExpression(carburetorTransformation.transformExpression(iBinaryExpression.getRightExpression() as Expression, "BinaryExpression:rightExpression"))
    }

    @Override
    void visitTernaryExpression(TernaryExpression iTernaryExpression) {
        iTernaryExpression.origCodeString = carburetorTransformation.codeString(iTernaryExpression)
        super.visitTernaryExpression(iTernaryExpression)
    }

    @Override
    void visitShortTernaryExpression(ElvisOperatorExpression iElvisOperatorExpression) {
        iElvisOperatorExpression.origCodeString = carburetorTransformation.codeString(iElvisOperatorExpression)
        iElvisOperatorExpression.getBooleanExpression().visit(this)
        iElvisOperatorExpression.getFalseExpression().visit(this)
    }

    @Override
    void visitPostfixExpression(PostfixExpression iPostfixExpression) {
        iPostfixExpression.origCodeString = carburetorTransformation.codeString(iPostfixExpression)
        super.visitPostfixExpression(iPostfixExpression)
    }

    @Override
    void visitPrefixExpression(PrefixExpression iPrefixExpression) {
        iPrefixExpression.origCodeString = carburetorTransformation.codeString(iPrefixExpression)
        super.visitPrefixExpression(iPrefixExpression)
    }

    @Override
    void visitBooleanExpression(BooleanExpression iBooleanExpression) {
        iBooleanExpression.origCodeString = carburetorTransformation.codeString(iBooleanExpression)
        super.visitBooleanExpression(iBooleanExpression)
    }

    @Override
    void visitNotExpression(NotExpression iNotExpression) {
        iNotExpression.origCodeString = carburetorTransformation.codeString(iNotExpression)
        super.visitNotExpression(iNotExpression)
    }

    @Override
    void visitClosureExpression(ClosureExpression iClosureExpression) {
        iClosureExpression.origCodeString = carburetorTransformation.codeString(iClosureExpression)
        super.visitClosureExpression(iClosureExpression)
        iClosureExpression.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iClosureExpression.getCode(), iClosureExpression.getClass().getSimpleName() + ":code")))
    }

    @Override
    void visitTupleExpression(TupleExpression iTupleExpression) {
        iTupleExpression.origCodeString = carburetorTransformation.codeString(iTupleExpression)
        super.visitTupleExpression(iTupleExpression)
        transformExpressionList(iTupleExpression.getExpressions(), iTupleExpression.getClass().getSimpleName() + ":expressions")
    }

    @Override
    void visitListExpression(ListExpression iListExpression) {
        iListExpression.origCodeString = carburetorTransformation.codeString(iListExpression)
        super.visitListExpression(iListExpression)
        transformExpressionList(iListExpression.getExpressions(), iListExpression.getClass().getSimpleName() + ":expressions")
    }

    @Override
    void visitArrayExpression(ArrayExpression iArrayExpression) {
        iArrayExpression.origCodeString = carburetorTransformation.codeString(iArrayExpression)
        super.visitArrayExpression(iArrayExpression)
        transformExpressionList(iArrayExpression.getExpressions(), iArrayExpression.getClass().getSimpleName() + ":expressions")
        transformExpressionList(iArrayExpression.getSizeExpression(), iArrayExpression.getClass().getSimpleName() + ":sizeExpression")
    }

    @Override
    void visitMapExpression(MapExpression iMapExpression) {
        iMapExpression.origCodeString = carburetorTransformation.codeString(iMapExpression)
        super.visitMapExpression(iMapExpression)
    }

    @Override
    void visitMapEntryExpression(MapEntryExpression iMapEntryExpression) {
        iMapEntryExpression.origCodeString = carburetorTransformation.codeString(iMapEntryExpression)
        super.visitMapEntryExpression(iMapEntryExpression)
        iMapEntryExpression.setKeyExpression(carburetorTransformation.transformExpression(iMapEntryExpression.getKeyExpression(), iMapEntryExpression.getClass().getSimpleName() + ":keyExpression"))
        iMapEntryExpression.setValueExpression(carburetorTransformation.transformExpression(iMapEntryExpression.getValueExpression(), iMapEntryExpression.getClass().getSimpleName() + ":valueExpression"))
    }

    @Override
    void visitRangeExpression(RangeExpression iRangeExpression) {
        iRangeExpression.origCodeString = carburetorTransformation.codeString(iRangeExpression)
        super.visitRangeExpression(iRangeExpression)
    }

    @Override
    void visitSpreadExpression(SpreadExpression iSpreadExpression) {
        iSpreadExpression.origCodeString = carburetorTransformation.codeString(iSpreadExpression)
        super.visitSpreadExpression(iSpreadExpression)
    }


    @Override
    void visitSpreadMapExpression(SpreadMapExpression iSpreadMapExpression) {
        iSpreadMapExpression.origCodeString = carburetorTransformation.codeString(iSpreadMapExpression)
        super.visitSpreadMapExpression(iSpreadMapExpression)
    }


    @Override
    void visitMethodPointerExpression(MethodPointerExpression iMethodPointerExpression) {
        iMethodPointerExpression.origCodeString = carburetorTransformation.codeString(iMethodPointerExpression)
        super.visitMethodPointerExpression(iMethodPointerExpression)
    }

    @Override
    void visitUnaryMinusExpression(UnaryMinusExpression iUnaryMinusExpression) {
        iUnaryMinusExpression.origCodeString = carburetorTransformation.codeString(iUnaryMinusExpression)
        super.visitUnaryMinusExpression(iUnaryMinusExpression)
    }


    @Override
    void visitUnaryPlusExpression(UnaryPlusExpression iUnaryPlusExpression) {
        iUnaryPlusExpression.origCodeString = carburetorTransformation.codeString(iUnaryPlusExpression)
        super.visitUnaryPlusExpression(iUnaryPlusExpression)
    }

    @Override
    void visitBitwiseNegationExpression(BitwiseNegationExpression iBitwiseNegationExpression) {
        iBitwiseNegationExpression.origCodeString = carburetorTransformation.codeString(iBitwiseNegationExpression)
        super.visitBitwiseNegationExpression(iBitwiseNegationExpression)
    }

    @Override
    void visitCastExpression(CastExpression iCastExpression) {
        iCastExpression.origCodeString = carburetorTransformation.codeString(iCastExpression)
        super.visitCastExpression(iCastExpression)
    }

    @Override
    void visitConstantExpression(ConstantExpression iConstantExpression) {
        iConstantExpression.origCodeString = carburetorTransformation.codeString(iConstantExpression)
        super.visitConstantExpression(iConstantExpression)
    }

    @Override
    void visitClassExpression(ClassExpression iClassExpression) {
        iClassExpression.origCodeString = carburetorTransformation.codeString(iClassExpression)
        super.visitClassExpression(iClassExpression)
    }

    @Override
    void visitVariableExpression(VariableExpression iVariableExpression) {
        iVariableExpression.origCodeString = carburetorTransformation.codeString(iVariableExpression)
        super.visitVariableExpression(iVariableExpression)
    }

    @Override
    void visitDeclarationExpression(DeclarationExpression iDeclarationExpression) {
        iDeclarationExpression.origCodeString = carburetorTransformation.codeString(iDeclarationExpression)
        iDeclarationExpression.getRightExpression().visit(this)
        iDeclarationExpression.setRightExpression(carburetorTransformation.transformExpression(iDeclarationExpression.getRightExpression(), iDeclarationExpression.getClass().getSimpleName() + ":rightExpression"))
    }

    @Override
    void visitPropertyExpression(PropertyExpression iPropertyExpression) {
        iPropertyExpression.origCodeString = carburetorTransformation.codeString(iPropertyExpression)
        super.visitPropertyExpression(iPropertyExpression)
    }

    @Override
    void visitAttributeExpression(AttributeExpression iAttributeExpression) {
        iAttributeExpression.origCodeString = carburetorTransformation.codeString(iAttributeExpression)
        super.visitAttributeExpression(iAttributeExpression)
    }

    @Override
    void visitFieldExpression(FieldExpression iFieldExpression) {
        iFieldExpression.origCodeString = carburetorTransformation.codeString(iFieldExpression)
        super.visitFieldExpression(iFieldExpression)
    }

    @Override
    void visitGStringExpression(GStringExpression iGStringExpression) {
        iGStringExpression.origCodeString = carburetorTransformation.codeString(iGStringExpression)
        super.visitGStringExpression(iGStringExpression)
        transformExpressionList(iGStringExpression.getValues(), iGStringExpression.getClass().getSimpleName() + ":values")
    }

    @Override
    void visitCatchStatement(CatchStatement iCatchStatement) {
        iCatchStatement.origCodeString = carburetorTransformation.codeString(iCatchStatement)
        super.visitCatchStatement(iCatchStatement)
        iCatchStatement.setCode(GeneralUtils.block(new VariableScope(), carburetorTransformation.transformStatement(iCatchStatement.getCode(), iCatchStatement.getClass().getSimpleName() + ":code")))
    }

    @Override
    void visitArgumentlistExpression(ArgumentListExpression iArgumentListExpression) {
        iArgumentListExpression.origCodeString = carburetorTransformation.codeString(iArgumentListExpression)
        super.visitArgumentlistExpression(iArgumentListExpression)
        transformExpressionList(iArgumentListExpression.getExpressions(), iArgumentListExpression.getClass().getSimpleName() + ":expressions")
    }

    @Override
    void visitClosureListExpression(ClosureListExpression iClosureListExpression) {
        iClosureListExpression.origCodeString = carburetorTransformation.codeString(iClosureListExpression)
        super.visitClosureListExpression(iClosureListExpression)
        transformExpressionList(iClosureListExpression.getExpressions(), iClosureListExpression.getClass().getSimpleName() + ":expressions")
    }

}
