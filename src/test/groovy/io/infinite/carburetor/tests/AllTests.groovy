package io.infinite.carburetor.tests

import groovy.transform.Memoized
import io.infinite.carburetor.TestEngine
import org.junit.Test
import org.slf4j.MDC

class AllTests extends GroovyTestCase {

    GroovyClassLoader groovyClassLoader = new GroovyClassLoader()

    void test() {
        executeTests()
    }

    void executeTests() {

        def testInstance = getTestInstance("tests", "VisitBlockStatement.groovy")
        testInstance.visitBlockStatementNoneLevel()
        testInstance.visitBlockStatementMethodErrorLevel()
        testInstance.visitBlockStatementMethodLevel()
        testInstance.visitBlockStatementStatementLevel()
        testInstance.visitBlockStatementExpressionLevel()

        getTestInstance("tests", "VisitForLoop.groovy").visitForLoopNoneLevel()
        getTestInstance("tests", "VisitForLoop.groovy").visitForLoopMethodErrorLevel()
        getTestInstance("tests", "VisitForLoop.groovy").visitForLoopMethodLevel()
        getTestInstance("tests", "VisitForLoop.groovy").visitForLoopStatementLevel()
        getTestInstance("tests", "VisitForLoop.groovy").visitForLoopExpressionLevel()
        getTestInstance("tests", "VisitWhileLoop.groovy").visitWhileLoopNoneLevel()
        getTestInstance("tests", "VisitWhileLoop.groovy").visitWhileLoopMethodErrorLevel()
        getTestInstance("tests", "VisitWhileLoop.groovy").visitWhileLoopMethodLevel()
        getTestInstance("tests", "VisitWhileLoop.groovy").visitWhileLoopStatementLevel()
        getTestInstance("tests", "VisitWhileLoop.groovy").visitWhileLoopExpressionLevel()
        getTestInstance("tests", "VisitDoWhileLoop.groovy").visitDoWhileLoopNoneLevel()
        getTestInstance("tests", "VisitDoWhileLoop.groovy").visitDoWhileLoopMethodErrorLevel()
        getTestInstance("tests", "VisitDoWhileLoop.groovy").visitDoWhileLoopMethodLevel()
        getTestInstance("tests", "VisitDoWhileLoop.groovy").visitDoWhileLoopStatementLevel()
        getTestInstance("tests", "VisitDoWhileLoop.groovy").visitDoWhileLoopExpressionLevel()
        getTestInstance("tests", "VisitIfElse.groovy").visitIfElseNoneLevel()
        getTestInstance("tests", "VisitIfElse.groovy").visitIfElseMethodErrorLevel()
        getTestInstance("tests", "VisitIfElse.groovy").visitIfElseMethodLevel()
        getTestInstance("tests", "VisitIfElse.groovy").visitIfElseStatementLevel()
        getTestInstance("tests", "VisitIfElse.groovy").visitIfElseExpressionLevel()
        getTestInstance("tests", "VisitExpressionStatement.groovy").visitExpressionStatementNoneLevel()
        getTestInstance("tests", "VisitExpressionStatement.groovy").visitExpressionStatementMethodErrorLevel()
        getTestInstance("tests", "VisitExpressionStatement.groovy").visitExpressionStatementMethodLevel()
        getTestInstance("tests", "VisitExpressionStatement.groovy").visitExpressionStatementStatementLevel()
        getTestInstance("tests", "VisitExpressionStatement.groovy").visitExpressionStatementExpressionLevel()
        getTestInstance("tests", "VisitReturnStatement.groovy").visitReturnStatementNoneLevel()
        getTestInstance("tests", "VisitReturnStatement.groovy").visitReturnStatementMethodErrorLevel()
        getTestInstance("tests", "VisitReturnStatement.groovy").visitReturnStatementMethodLevel()
        getTestInstance("tests", "VisitReturnStatement.groovy").visitReturnStatementStatementLevel()
        getTestInstance("tests", "VisitReturnStatement.groovy").visitReturnStatementExpressionLevel()
        getTestInstance("tests", "VisitAssertStatement.groovy").visitAssertStatementNoneLevel()
        getTestInstance("tests", "VisitAssertStatement.groovy").visitAssertStatementMethodErrorLevel()
        getTestInstance("tests", "VisitAssertStatement.groovy").visitAssertStatementMethodLevel()
        getTestInstance("tests", "VisitAssertStatement.groovy").visitAssertStatementStatementLevel()
        getTestInstance("tests", "VisitAssertStatement.groovy").visitAssertStatementExpressionLevel()
        getTestInstance("tests", "VisitTryCatchFinally.groovy").visitTryCatchFinallyNoneLevel()
        getTestInstance("tests", "VisitTryCatchFinally.groovy").visitTryCatchFinallyMethodErrorLevel()
        getTestInstance("tests", "VisitTryCatchFinally.groovy").visitTryCatchFinallyMethodLevel()
        getTestInstance("tests", "VisitTryCatchFinally.groovy").visitTryCatchFinallyStatementLevel()
        getTestInstance("tests", "VisitTryCatchFinally.groovy").visitTryCatchFinallyExpressionLevel()
        getTestInstance("tests", "VisitEmptyStatement.groovy").visitEmptyStatementNoneLevel()
        getTestInstance("tests", "VisitEmptyStatement.groovy").visitEmptyStatementMethodErrorLevel()
        getTestInstance("tests", "VisitEmptyStatement.groovy").visitEmptyStatementMethodLevel()
        getTestInstance("tests", "VisitEmptyStatement.groovy").visitEmptyStatementStatementLevel()
        getTestInstance("tests", "VisitEmptyStatement.groovy").visitEmptyStatementExpressionLevel()
        getTestInstance("tests", "VisitSwitch.groovy").visitSwitchNoneLevel()
        getTestInstance("tests", "VisitSwitch.groovy").visitSwitchMethodErrorLevel()
        getTestInstance("tests", "VisitSwitch.groovy").visitSwitchMethodLevel()
        getTestInstance("tests", "VisitSwitch.groovy").visitSwitchStatementLevel()
        getTestInstance("tests", "VisitSwitch.groovy").visitSwitchExpressionLevel()
        getTestInstance("tests", "VisitCaseStatement.groovy").visitCaseStatementNoneLevel()
        getTestInstance("tests", "VisitCaseStatement.groovy").visitCaseStatementMethodErrorLevel()
        getTestInstance("tests", "VisitCaseStatement.groovy").visitCaseStatementMethodLevel()
        getTestInstance("tests", "VisitCaseStatement.groovy").visitCaseStatementStatementLevel()
        getTestInstance("tests", "VisitCaseStatement.groovy").visitCaseStatementExpressionLevel()
        getTestInstance("tests", "VisitBreakStatement.groovy").visitBreakStatementNoneLevel()
        getTestInstance("tests", "VisitBreakStatement.groovy").visitBreakStatementMethodErrorLevel()
        getTestInstance("tests", "VisitBreakStatement.groovy").visitBreakStatementMethodLevel()
        getTestInstance("tests", "VisitBreakStatement.groovy").visitBreakStatementStatementLevel()
        getTestInstance("tests", "VisitBreakStatement.groovy").visitBreakStatementExpressionLevel()
        getTestInstance("tests", "VisitContinueStatement.groovy").visitContinueStatementNoneLevel()
        getTestInstance("tests", "VisitContinueStatement.groovy").visitContinueStatementMethodErrorLevel()
        getTestInstance("tests", "VisitContinueStatement.groovy").visitContinueStatementMethodLevel()
        getTestInstance("tests", "VisitContinueStatement.groovy").visitContinueStatementStatementLevel()
        getTestInstance("tests", "VisitContinueStatement.groovy").visitContinueStatementExpressionLevel()
        getTestInstance("tests", "VisitSynchronizedStatement.groovy").visitSynchronizedStatementNoneLevel()
        getTestInstance("tests", "VisitSynchronizedStatement.groovy").visitSynchronizedStatementMethodErrorLevel()
        getTestInstance("tests", "VisitSynchronizedStatement.groovy").visitSynchronizedStatementMethodLevel()
        getTestInstance("tests", "VisitSynchronizedStatement.groovy").visitSynchronizedStatementStatementLevel()
        getTestInstance("tests", "VisitSynchronizedStatement.groovy").visitSynchronizedStatementExpressionLevel()
        getTestInstance("tests", "VisitThrowStatement.groovy").visitThrowStatementNoneLevel()
        getTestInstance("tests", "VisitThrowStatement.groovy").visitThrowStatementMethodErrorLevel()
        getTestInstance("tests", "VisitThrowStatement.groovy").visitThrowStatementMethodLevel()
        getTestInstance("tests", "VisitThrowStatement.groovy").visitThrowStatementStatementLevel()
        getTestInstance("tests", "VisitThrowStatement.groovy").visitThrowStatementExpressionLevel()
        getTestInstance("tests", "VisitMethodCallExpression.groovy").visitMethodCallExpressionNoneLevel()
        getTestInstance("tests", "VisitMethodCallExpression.groovy").visitMethodCallExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitMethodCallExpression.groovy").visitMethodCallExpressionMethodLevel()
        getTestInstance("tests", "VisitMethodCallExpression.groovy").visitMethodCallExpressionStatementLevel()
        getTestInstance("tests", "VisitMethodCallExpression.groovy").visitMethodCallExpressionExpressionLevel()
        getTestInstance("tests", "VisitStaticMethodCallExpression.groovy").visitStaticMethodCallExpressionNoneLevel()
        getTestInstance("tests", "VisitStaticMethodCallExpression.groovy").visitStaticMethodCallExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitStaticMethodCallExpression.groovy").visitStaticMethodCallExpressionMethodLevel()
        getTestInstance("tests", "VisitStaticMethodCallExpression.groovy").visitStaticMethodCallExpressionStatementLevel()
        getTestInstance("tests", "VisitStaticMethodCallExpression.groovy").visitStaticMethodCallExpressionExpressionLevel()
        getTestInstance("tests", "VisitConstructorCallExpression.groovy").visitConstructorCallExpressionNoneLevel()
        getTestInstance("tests", "VisitConstructorCallExpression.groovy").visitConstructorCallExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitConstructorCallExpression.groovy").visitConstructorCallExpressionMethodLevel()
        getTestInstance("tests", "VisitConstructorCallExpression.groovy").visitConstructorCallExpressionStatementLevel()
        getTestInstance("tests", "VisitConstructorCallExpression.groovy").visitConstructorCallExpressionExpressionLevel()
        getTestInstance("tests", "VisitBinaryExpression.groovy").visitBinaryExpressionNoneLevel()
        getTestInstance("tests", "VisitBinaryExpression.groovy").visitBinaryExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitBinaryExpression.groovy").visitBinaryExpressionMethodLevel()
        getTestInstance("tests", "VisitBinaryExpression.groovy").visitBinaryExpressionStatementLevel()
        getTestInstance("tests", "VisitBinaryExpression.groovy").visitBinaryExpressionExpressionLevel()
        getTestInstance("tests", "VisitTernaryExpression.groovy").visitTernaryExpressionNoneLevel()
        getTestInstance("tests", "VisitTernaryExpression.groovy").visitTernaryExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitTernaryExpression.groovy").visitTernaryExpressionMethodLevel()
        getTestInstance("tests", "VisitTernaryExpression.groovy").visitTernaryExpressionStatementLevel()
        getTestInstance("tests", "VisitTernaryExpression.groovy").visitTernaryExpressionExpressionLevel()
        getTestInstance("tests", "VisitShortTernaryExpression.groovy").visitShortTernaryExpressionNoneLevel()
        getTestInstance("tests", "VisitShortTernaryExpression.groovy").visitShortTernaryExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitShortTernaryExpression.groovy").visitShortTernaryExpressionMethodLevel()
        getTestInstance("tests", "VisitShortTernaryExpression.groovy").visitShortTernaryExpressionStatementLevel()
        getTestInstance("tests", "VisitShortTernaryExpression.groovy").visitShortTernaryExpressionExpressionLevel()
        getTestInstance("tests", "VisitPostfixExpression.groovy").visitPostfixExpressionNoneLevel()
        getTestInstance("tests", "VisitPostfixExpression.groovy").visitPostfixExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitPostfixExpression.groovy").visitPostfixExpressionMethodLevel()
        getTestInstance("tests", "VisitPostfixExpression.groovy").visitPostfixExpressionStatementLevel()
        getTestInstance("tests", "VisitPostfixExpression.groovy").visitPostfixExpressionExpressionLevel()
        getTestInstance("tests", "VisitPrefixExpression.groovy").visitPrefixExpressionNoneLevel()
        getTestInstance("tests", "VisitPrefixExpression.groovy").visitPrefixExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitPrefixExpression.groovy").visitPrefixExpressionMethodLevel()
        getTestInstance("tests", "VisitPrefixExpression.groovy").visitPrefixExpressionStatementLevel()
        getTestInstance("tests", "VisitPrefixExpression.groovy").visitPrefixExpressionExpressionLevel()
        getTestInstance("tests", "VisitBooleanExpression.groovy").visitBooleanExpressionNoneLevel()
        getTestInstance("tests", "VisitBooleanExpression.groovy").visitBooleanExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitBooleanExpression.groovy").visitBooleanExpressionMethodLevel()
        getTestInstance("tests", "VisitBooleanExpression.groovy").visitBooleanExpressionStatementLevel()
        getTestInstance("tests", "VisitBooleanExpression.groovy").visitBooleanExpressionExpressionLevel()
        getTestInstance("tests", "VisitNotExpression.groovy").visitNotExpressionNoneLevel()
        getTestInstance("tests", "VisitNotExpression.groovy").visitNotExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitNotExpression.groovy").visitNotExpressionMethodLevel()
        getTestInstance("tests", "VisitNotExpression.groovy").visitNotExpressionStatementLevel()
        getTestInstance("tests", "VisitNotExpression.groovy").visitNotExpressionExpressionLevel()
        getTestInstance("tests", "VisitClosureExpression.groovy").visitClosureExpressionNoneLevel()
        getTestInstance("tests", "VisitClosureExpression.groovy").visitClosureExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitClosureExpression.groovy").visitClosureExpressionMethodLevel()
        getTestInstance("tests", "VisitClosureExpression.groovy").visitClosureExpressionStatementLevel()
        getTestInstance("tests", "VisitClosureExpression.groovy").visitClosureExpressionExpressionLevel()
        getTestInstance("tests", "VisitTupleExpression.groovy").visitTupleExpressionNoneLevel()
        getTestInstance("tests", "VisitTupleExpression.groovy").visitTupleExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitTupleExpression.groovy").visitTupleExpressionMethodLevel()
        getTestInstance("tests", "VisitTupleExpression.groovy").visitTupleExpressionStatementLevel()
        getTestInstance("tests", "VisitTupleExpression.groovy").visitTupleExpressionExpressionLevel()
        getTestInstance("tests", "VisitListExpression.groovy").visitListExpressionNoneLevel()
        getTestInstance("tests", "VisitListExpression.groovy").visitListExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitListExpression.groovy").visitListExpressionMethodLevel()
        getTestInstance("tests", "VisitListExpression.groovy").visitListExpressionStatementLevel()
        getTestInstance("tests", "VisitListExpression.groovy").visitListExpressionExpressionLevel()
        getTestInstance("tests", "VisitArrayExpression.groovy").visitArrayExpressionNoneLevel()
        getTestInstance("tests", "VisitArrayExpression.groovy").visitArrayExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitArrayExpression.groovy").visitArrayExpressionMethodLevel()
        getTestInstance("tests", "VisitArrayExpression.groovy").visitArrayExpressionStatementLevel()
        getTestInstance("tests", "VisitArrayExpression.groovy").visitArrayExpressionExpressionLevel()
        getTestInstance("tests", "VisitMapExpression.groovy").visitMapExpressionNoneLevel()
        getTestInstance("tests", "VisitMapExpression.groovy").visitMapExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitMapExpression.groovy").visitMapExpressionMethodLevel()
        getTestInstance("tests", "VisitMapExpression.groovy").visitMapExpressionStatementLevel()
        getTestInstance("tests", "VisitMapExpression.groovy").visitMapExpressionExpressionLevel()
        getTestInstance("tests", "VisitMapEntryExpression.groovy").visitMapEntryExpressionNoneLevel()
        getTestInstance("tests", "VisitMapEntryExpression.groovy").visitMapEntryExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitMapEntryExpression.groovy").visitMapEntryExpressionMethodLevel()
        getTestInstance("tests", "VisitMapEntryExpression.groovy").visitMapEntryExpressionStatementLevel()
        getTestInstance("tests", "VisitMapEntryExpression.groovy").visitMapEntryExpressionExpressionLevel()
        getTestInstance("tests", "VisitRangeExpression.groovy").visitRangeExpressionNoneLevel()
        getTestInstance("tests", "VisitRangeExpression.groovy").visitRangeExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitRangeExpression.groovy").visitRangeExpressionMethodLevel()
        getTestInstance("tests", "VisitRangeExpression.groovy").visitRangeExpressionStatementLevel()
        getTestInstance("tests", "VisitRangeExpression.groovy").visitRangeExpressionExpressionLevel()
        getTestInstance("tests", "VisitSpreadExpression.groovy").visitSpreadExpressionNoneLevel()
        getTestInstance("tests", "VisitSpreadExpression.groovy").visitSpreadExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitSpreadExpression.groovy").visitSpreadExpressionMethodLevel()
        getTestInstance("tests", "VisitSpreadExpression.groovy").visitSpreadExpressionStatementLevel()
        getTestInstance("tests", "VisitSpreadExpression.groovy").visitSpreadExpressionExpressionLevel()
        getTestInstance("tests", "VisitSpreadMapExpression.groovy").visitSpreadMapExpressionNoneLevel()
        getTestInstance("tests", "VisitSpreadMapExpression.groovy").visitSpreadMapExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitSpreadMapExpression.groovy").visitSpreadMapExpressionMethodLevel()
        getTestInstance("tests", "VisitSpreadMapExpression.groovy").visitSpreadMapExpressionStatementLevel()
        getTestInstance("tests", "VisitSpreadMapExpression.groovy").visitSpreadMapExpressionExpressionLevel()
        getTestInstance("tests", "VisitMethodPointerExpression.groovy").visitMethodPointerExpressionNoneLevel()
        getTestInstance("tests", "VisitMethodPointerExpression.groovy").visitMethodPointerExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitMethodPointerExpression.groovy").visitMethodPointerExpressionMethodLevel()
        getTestInstance("tests", "VisitMethodPointerExpression.groovy").visitMethodPointerExpressionStatementLevel()
        getTestInstance("tests", "VisitMethodPointerExpression.groovy").visitMethodPointerExpressionExpressionLevel()
        getTestInstance("tests", "VisitUnaryMinusExpression.groovy").visitUnaryMinusExpressionNoneLevel()
        getTestInstance("tests", "VisitUnaryMinusExpression.groovy").visitUnaryMinusExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitUnaryMinusExpression.groovy").visitUnaryMinusExpressionMethodLevel()
        getTestInstance("tests", "VisitUnaryMinusExpression.groovy").visitUnaryMinusExpressionStatementLevel()
        getTestInstance("tests", "VisitUnaryMinusExpression.groovy").visitUnaryMinusExpressionExpressionLevel()
        getTestInstance("tests", "VisitUnaryPlusExpression.groovy").visitUnaryPlusExpressionNoneLevel()
        getTestInstance("tests", "VisitUnaryPlusExpression.groovy").visitUnaryPlusExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitUnaryPlusExpression.groovy").visitUnaryPlusExpressionMethodLevel()
        getTestInstance("tests", "VisitUnaryPlusExpression.groovy").visitUnaryPlusExpressionStatementLevel()
        getTestInstance("tests", "VisitUnaryPlusExpression.groovy").visitUnaryPlusExpressionExpressionLevel()
        getTestInstance("tests", "VisitBitwiseNegationExpression.groovy").visitBitwiseNegationExpressionNoneLevel()
        getTestInstance("tests", "VisitBitwiseNegationExpression.groovy").visitBitwiseNegationExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitBitwiseNegationExpression.groovy").visitBitwiseNegationExpressionMethodLevel()
        getTestInstance("tests", "VisitBitwiseNegationExpression.groovy").visitBitwiseNegationExpressionStatementLevel()
        getTestInstance("tests", "VisitBitwiseNegationExpression.groovy").visitBitwiseNegationExpressionExpressionLevel()
        getTestInstance("tests", "VisitCastExpression.groovy").visitCastExpressionNoneLevel()
        getTestInstance("tests", "VisitCastExpression.groovy").visitCastExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitCastExpression.groovy").visitCastExpressionMethodLevel()
        getTestInstance("tests", "VisitCastExpression.groovy").visitCastExpressionStatementLevel()
        getTestInstance("tests", "VisitCastExpression.groovy").visitCastExpressionExpressionLevel()
        getTestInstance("tests", "VisitConstantExpression.groovy").visitConstantExpressionNoneLevel()
        getTestInstance("tests", "VisitConstantExpression.groovy").visitConstantExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitConstantExpression.groovy").visitConstantExpressionMethodLevel()
        getTestInstance("tests", "VisitConstantExpression.groovy").visitConstantExpressionStatementLevel()
        getTestInstance("tests", "VisitConstantExpression.groovy").visitConstantExpressionExpressionLevel()
        getTestInstance("tests", "VisitClassExpression.groovy").visitClassExpressionNoneLevel()
        getTestInstance("tests", "VisitClassExpression.groovy").visitClassExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitClassExpression.groovy").visitClassExpressionMethodLevel()
        getTestInstance("tests", "VisitClassExpression.groovy").visitClassExpressionStatementLevel()
        getTestInstance("tests", "VisitClassExpression.groovy").visitClassExpressionExpressionLevel()
        getTestInstance("tests", "VisitVariableExpression.groovy").visitVariableExpressionNoneLevel()
        getTestInstance("tests", "VisitVariableExpression.groovy").visitVariableExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitVariableExpression.groovy").visitVariableExpressionMethodLevel()
        getTestInstance("tests", "VisitVariableExpression.groovy").visitVariableExpressionStatementLevel()
        getTestInstance("tests", "VisitVariableExpression.groovy").visitVariableExpressionExpressionLevel()
        getTestInstance("tests", "VisitDeclarationExpression.groovy").visitDeclarationExpressionNoneLevel()
        getTestInstance("tests", "VisitDeclarationExpression.groovy").visitDeclarationExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitDeclarationExpression.groovy").visitDeclarationExpressionMethodLevel()
        getTestInstance("tests", "VisitDeclarationExpression.groovy").visitDeclarationExpressionStatementLevel()
        getTestInstance("tests", "VisitDeclarationExpression.groovy").visitDeclarationExpressionExpressionLevel()
        getTestInstance("tests", "VisitPropertyExpression.groovy").visitPropertyExpressionNoneLevel()
        getTestInstance("tests", "VisitPropertyExpression.groovy").visitPropertyExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitPropertyExpression.groovy").visitPropertyExpressionMethodLevel()
        getTestInstance("tests", "VisitPropertyExpression.groovy").visitPropertyExpressionStatementLevel()
        getTestInstance("tests", "VisitPropertyExpression.groovy").visitPropertyExpressionExpressionLevel()
        getTestInstance("tests", "VisitAttributeExpression.groovy").visitAttributeExpressionNoneLevel()
        getTestInstance("tests", "VisitAttributeExpression.groovy").visitAttributeExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitAttributeExpression.groovy").visitAttributeExpressionMethodLevel()
        getTestInstance("tests", "VisitAttributeExpression.groovy").visitAttributeExpressionStatementLevel()
        getTestInstance("tests", "VisitAttributeExpression.groovy").visitAttributeExpressionExpressionLevel()
        getTestInstance("tests", "VisitFieldExpression.groovy").visitFieldExpressionNoneLevel()
        getTestInstance("tests", "VisitFieldExpression.groovy").visitFieldExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitFieldExpression.groovy").visitFieldExpressionMethodLevel()
        getTestInstance("tests", "VisitFieldExpression.groovy").visitFieldExpressionStatementLevel()
        getTestInstance("tests", "VisitFieldExpression.groovy").visitFieldExpressionExpressionLevel()
        getTestInstance("tests", "VisitGStringExpression.groovy").visitGStringExpressionNoneLevel()
        getTestInstance("tests", "VisitGStringExpression.groovy").visitGStringExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitGStringExpression.groovy").visitGStringExpressionMethodLevel()
        getTestInstance("tests", "VisitGStringExpression.groovy").visitGStringExpressionStatementLevel()
        getTestInstance("tests", "VisitGStringExpression.groovy").visitGStringExpressionExpressionLevel()
        getTestInstance("tests", "VisitListOfExpressions.groovy").visitListOfExpressionsNoneLevel()
        getTestInstance("tests", "VisitListOfExpressions.groovy").visitListOfExpressionsMethodErrorLevel()
        getTestInstance("tests", "VisitListOfExpressions.groovy").visitListOfExpressionsMethodLevel()
        getTestInstance("tests", "VisitListOfExpressions.groovy").visitListOfExpressionsStatementLevel()
        getTestInstance("tests", "VisitListOfExpressions.groovy").visitListOfExpressionsExpressionLevel()
        getTestInstance("tests", "VisitCatchStatement.groovy").visitCatchStatementNoneLevel()
        getTestInstance("tests", "VisitCatchStatement.groovy").visitCatchStatementMethodErrorLevel()
        getTestInstance("tests", "VisitCatchStatement.groovy").visitCatchStatementMethodLevel()
        getTestInstance("tests", "VisitCatchStatement.groovy").visitCatchStatementStatementLevel()
        getTestInstance("tests", "VisitCatchStatement.groovy").visitCatchStatementExpressionLevel()
        getTestInstance("tests", "VisitArgumentlistExpression.groovy").visitArgumentlistExpressionNoneLevel()
        getTestInstance("tests", "VisitArgumentlistExpression.groovy").visitArgumentlistExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitArgumentlistExpression.groovy").visitArgumentlistExpressionMethodLevel()
        getTestInstance("tests", "VisitArgumentlistExpression.groovy").visitArgumentlistExpressionStatementLevel()
        getTestInstance("tests", "VisitArgumentlistExpression.groovy").visitArgumentlistExpressionExpressionLevel()
        getTestInstance("tests", "VisitClosureListExpression.groovy").visitClosureListExpressionNoneLevel()
        getTestInstance("tests", "VisitClosureListExpression.groovy").visitClosureListExpressionMethodErrorLevel()
        getTestInstance("tests", "VisitClosureListExpression.groovy").visitClosureListExpressionMethodLevel()
        getTestInstance("tests", "VisitClosureListExpression.groovy").visitClosureListExpressionStatementLevel()
        getTestInstance("tests", "VisitClosureListExpression.groovy").visitClosureListExpressionExpressionLevel()
        getTestInstance("issues", "AnonymousInnerClassConstructor.groovy").runTest()
        getTestInstance("others", "RuntimeExceptionTEst.groovy").runTest()
    }

    @Memoized
    def getTestInstance(String sectionName, String testScriptName) {
        def testInstance = groovyClassLoader.parseClass(getTestScript(sectionName, testScriptName)).newInstance()
        MDC.put("unitName", "EXECUTION_${testScriptName}")
        return testInstance
    }

    File getTestScript(String sectionName, String testScriptName) {
        ClassLoader classLoader = getClass().getClassLoader()
        File file = new File(classLoader.getResource(sectionName + "/" + testScriptName).getFile())
        return file
    }

    void run1() {
        /*
        new ItVariable().test()
        new Bar()
        new ThreadSafety().start()
        new ThreadSafety().start()
        RoundRobin roundRobin = new RoundRobin()
        roundRobin.add("Test")
        String test = ++roundRobin.iterator()
        assert test == "Test"
        new SubClass().bar("foo")
        new Suppress().test()
        new DuplicateException().test()
        BlackBoxEngine.getInstance(log).expressionEvaluation("", "", 0, 0, 0, 0, { StaticToString }, "", null)
        new ExceptionPlaintext().test()
        new DefaultBlackBoxLevel().foo()
        ToString toString = new ToString()
        assert toString.toString() == "io.infinite.blackbox.others.ToString@" + Integer.toHexString(toString.hashCode())
        new DelegateTest().test()
        new ErrorStrategies().test()
        new ClassAnnotation().someMethod()
        new Static().test()*/
    }


}

