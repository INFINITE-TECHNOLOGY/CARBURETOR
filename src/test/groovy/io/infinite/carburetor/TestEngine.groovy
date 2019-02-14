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
    void expressionExecutionOpen(MetaDataExpression metaDataExpression) {

    }

    @Override
    def handleExpressionEvaluationResult(Object expressionEvaluationResult) {
        return null
    }

    @Override
    Exception carburetorRuntimeExceptionHandle(Exception exception, MetaDataASTNode metaDataASTNode) {
        return new CarburetorRuntimeException(metaDataASTNode, exception)
    }

    @Override
    void statementExecutionOpen(MetaDataStatement metaDataStatement) {

    }

    @Override
    void methodExecutionOpen(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap) {

    }

    @Override
    void methodException(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap, Exception exception) {

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

}
