package io.infinite.carburetor

import groovy.util.logging.Slf4j
import io.infinite.carburetor.ast.MetaDataExpression
import io.infinite.carburetor.ast.MetaDataMethodNode
import io.infinite.carburetor.ast.MetaDataStatement

import java.lang.reflect.Field

@Slf4j
abstract class CarburetorEngine {

    abstract void expressionExecutionOpen(MetaDataExpression metaDataExpression)

    abstract handleExpressionEvaluationResult(Object expressionEvaluationResult)

    final Object expressionEvaluation(
            MetaDataExpression metaDataExpression,
            Closure expressionClosure,
            Object automaticThis
    ) {
        expressionExecutionOpen(metaDataExpression)
        try {
            ensureClosureEquivalency(expressionClosure, automaticThis)
            Object evaluationResult = expressionClosure.call()
            handleExpressionEvaluationResult(evaluationResult)
            return evaluationResult
        } finally {
            executionClose()
        }
    }

    abstract void statementExecutionOpen(MetaDataStatement metaDataStatement)

    abstract void methodExecutionOpen(
            MetaDataMethodNode metaDataMethodNode,
            Map<String, Object> methodArgumentMap
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
        ensureClosureEquivalency(iMethodClosure, iAutomaticThis)
        Object methodResult = iMethodClosure.call()
        handleMethodResult(methodResult)
        return methodResult
    }

    static final void ensureClosureEquivalency(Closure iClosure, Object iAutomaticThis) {
        Field thisObjectField = Closure.getDeclaredField('thisObject')
        Field ownerField = Closure.getDeclaredField('owner')
        thisObjectField.setAccessible(true)
        ownerField.setAccessible(true)
        thisObjectField.set(iClosure, iAutomaticThis)
        ownerField.set(iClosure, iAutomaticThis)
        iClosure.setDelegate(iAutomaticThis)
        iClosure.setResolveStrategy(Closure.DELEGATE_ONLY)
        ownerField.setAccessible(false)
        thisObjectField.setAccessible(false)
    }

    abstract void exception(Exception exception)

}
