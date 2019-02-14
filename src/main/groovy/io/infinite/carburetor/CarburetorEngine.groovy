package io.infinite.carburetor

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.infinite.supplies.ast.metadata.MetaDataASTNode
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement
import io.infinite.supplies.ast.other.ASTUtils

@Slf4j
@CompileStatic
/**
 * Requirements to Carburetor engines:
 * 1) Should support usage in static context (engine var is declared as static)
 * 2) Should be thread safe
 */
abstract class CarburetorEngine {

    abstract void expressionExecutionOpen(MetaDataExpression metaDataExpression)

    abstract handleExpressionEvaluationResult(Object expressionEvaluationResult)

    ASTUtils astUtils = new ASTUtils()

    final Object expressionEvaluation(
            MetaDataExpression metaDataExpression,
            Closure expressionClosure,
            Object automaticThis
    ) {
        expressionExecutionOpen(metaDataExpression)
        try {
            astUtils.ensureClosureEquivalency(expressionClosure, automaticThis)
            Object evaluationResult
            try {
                evaluationResult = expressionClosure.call()
            } catch (Exception exception) {
                throw carburetorRuntimeExceptionHandle(exception, metaDataExpression)
            }
            handleExpressionEvaluationResult(evaluationResult)
            return evaluationResult
        } finally {
            executionClose()
        }
    }

    abstract Exception carburetorRuntimeExceptionHandle(Exception exception, MetaDataASTNode metaDataASTNode)

    abstract void statementExecutionOpen(MetaDataStatement metaDataStatement)

    abstract void methodStart(
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

    abstract void executionClose()

    abstract void handleControlStatement(String controlStatementClassName)

    final void preprocessControlStatement(MetaDataStatement metaDataStatement) {
        statementExecutionOpen(metaDataStatement)
        executionClose()
        handleControlStatement(metaDataStatement.getStatementClassName())
    }

    abstract void handleMethodResult(Object methodResult)

    final Object executeMethod(Closure iMethodClosure, Object iAutomaticThis) {
        astUtils.ensureClosureEquivalency(iMethodClosure, iAutomaticThis)
        Object methodResult = iMethodClosure.call()
        handleMethodResult(methodResult)
        return methodResult
    }

}
