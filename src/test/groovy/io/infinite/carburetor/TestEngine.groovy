package io.infinite.carburetor

import groovy.transform.CompileStatic
import io.infinite.supplies.ast.metadata.MetaDataASTNode
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement

@CompileStatic
class TestEngine extends CarburetorEngine {

    static TestEngine getInstance() {
        return new TestEngine()
    }

    @Override
    void expressionStart(MetaDataExpression metaDataExpression) {

    }

    @Override
    void expressionEnd(MetaDataExpression metaDataExpression) {

    }

    @Override
    def handleExpressionResult(Object expressionEvaluationResult) {
        return null
    }

    @Override
    Exception handleException(Exception exception, MetaDataASTNode metaDataASTNode) {
        return new CarburetorRuntimeException(metaDataASTNode, exception)
    }

    @Override
    void statementStart(MetaDataStatement metaDataStatement) {

    }

    @Override
    void statementEnd(MetaDataStatement metaDataStatement) {

    }

    @Override
    void methodStart(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap) {

    }

    @Override
    void methodEnd(MetaDataMethodNode metaDataMethodNode) {

    }

    @Override
    void methodException(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap, Exception exception) {

    }

    @Override
    void handleControlStatement(String controlStatementClassName) {

    }

    @Override
    void handleMethodResult(Object methodResult) {

    }

}
