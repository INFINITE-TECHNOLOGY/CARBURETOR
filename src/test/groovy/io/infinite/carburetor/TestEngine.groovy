package io.infinite.carburetor

import io.infinite.carburetor.ast.MetaDataExpression
import io.infinite.carburetor.ast.MetaDataMethodNode
import io.infinite.carburetor.ast.MetaDataStatement

class TestEngine extends CarburetorEngine{

    static TestEngine getInstance() {
        return new TestEngine()
    }

    @Override
    void expressionExecutionOpen(MetaDataExpression metaDataExpression) {

    }

    @Override
    def handleExpressionEvaluationResult(Object expressionEvaluationResult) {
        return null
    }

    @Override
    void statementExecutionOpen(MetaDataStatement metaDataStatement) {

    }

    @Override
    void methodExecutionOpen(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap) {

    }

    @Override
    void executionClose() {

    }

    @Override
    void handleControlStatement(String controlStatementClassName) {

    }

    @Override
    void handleMethodResult(Object methodResult) {

    }

    @Override
    void exception(Exception exception) {

    }
}
