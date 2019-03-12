package io.infinite.carburetor

import groovy.transform.CompileDynamic
import io.infinite.supplies.ast.metadata.MetaDataASTNode
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement
import org.slf4j.Logger

@CompileDynamic
class TestEngine extends CarburetorEngine {

    TestEngine getInstance(Logger logger) {
        return new TestEngine()
    }

    @Override
    void expressionBegin(MetaDataExpression metaDataExpression) {

    }

    @Override
    void expressionEnd(MetaDataExpression metaDataExpression) {

    }

    @Override
    Object handleExpressionResult(Object expressionEvaluationResult, MetaDataExpression metaDataExpression) {
        return expressionEvaluationResult
    }

    @Override
    Exception handleException(Exception exception, MetaDataASTNode metaDataASTNode) {
        return exception
    }

    @Override
    void statementBegin(MetaDataStatement metaDataStatement) {

    }

    @Override
    void statementEnd(MetaDataStatement metaDataStatement) {

    }

    @Override
    void methodBegin(MetaDataMethodNode metaDataMethodNode, Map<String, Object> methodArgumentMap) {

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
