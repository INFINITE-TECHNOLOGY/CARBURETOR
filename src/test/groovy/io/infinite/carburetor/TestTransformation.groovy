package io.infinite.carburetor

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.GroovyASTTransformation

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
@Slf4j
class TestTransformation extends CarburetorTransformation {

    @Override
    Boolean excludeMethodNode(MethodNode methodNode) {
        return false
    }

    @Override
    void optionalDeclarations(ClassNode classNode) {

    }

    @Override
    Class getEngineFactoryClass() {
        return TestEngine.class
    }

    @Override
    Expression getEngineInitArgs() {
        MethodCallExpression.NO_ARGUMENTS
    }

    @Override
    void methodDeclarations(MethodNode methodNode) {

    }

    @Override
    void getAnnotationParameters() {

    }

    @Override
    String getEngineVarName() {
        return "testEngine"
    }
}
