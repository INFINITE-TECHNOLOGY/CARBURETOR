package io.infinite.carburetor

import groovy.util.logging.Slf4j
import io.infinite.supplies.ast.metadata.MetaDataASTNode
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement
import io.infinite.supplies.ast.other.ASTUtils

@Slf4j
/**
 * Requirements to Carburetor engines:
 * 1) Should support usage in static context (engine var is declared as static)
 * 2) Should be thread safe
 */
abstract class CarburetorEngine {

    abstract void expressionBegin(MetaDataExpression metaDataExpression)

    abstract void expressionEnd(MetaDataExpression metaDataExpression)

    abstract Object handleExpressionResult(Object expressionEvaluationResult, MetaDataExpression metaDataExpression)

    ASTUtils astUtils = new ASTUtils()

    Object expressionEvaluation(
            MetaDataExpression metaDataExpression,
            Closure expressionClosure,
            Object automaticThis
    ) {
        expressionBegin(metaDataExpression)
        try {
            astUtils.ensureClosureEquivalency(expressionClosure, automaticThis)
            Object evaluationResult
            try {
                evaluationResult = expressionClosure.call()
            } catch (Exception exception) {
                throw handleException(exception, metaDataExpression)
            }
            handleExpressionResult(evaluationResult, metaDataExpression)
            return evaluationResult
        } finally {
            expressionEnd(metaDataExpression)
        }
    }

    abstract Exception handleException(Exception exception, MetaDataASTNode metaDataASTNode)

    abstract void statementBegin(MetaDataStatement metaDataStatement)

    abstract void statementEnd(MetaDataStatement metaDataStatement)

    abstract void methodBegin(
            MetaDataMethodNode metaDataMethodNode,
            Map<String, Object> methodArgumentMap
    )

    abstract void methodEnd(
            MetaDataMethodNode metaDataMethodNode
    )

    abstract void methodException(
            MetaDataMethodNode metaDataMethodNode,
            Map<String, Object> methodArgumentMap,
            Exception exception
    )

    abstract void handleControlStatement(String controlStatementClassName)

    void preprocessControlStatement(MetaDataStatement metaDataStatement) {
        statementBegin(metaDataStatement)
        statementEnd(metaDataStatement)
        handleControlStatement(metaDataStatement.getStatementClassName())
    }

    abstract void handleMethodResult(Object methodResult)

    Object executeMethod(Closure iMethodClosure, Object iAutomaticThis) {
        astUtils.ensureClosureEquivalency(iMethodClosure, iAutomaticThis)
        Object methodResult = iMethodClosure.call()
        handleMethodResult(methodResult)
        return methodResult
    }

}
