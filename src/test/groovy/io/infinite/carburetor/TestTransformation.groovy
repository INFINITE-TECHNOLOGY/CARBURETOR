package io.infinite.carburetor

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.carburetor.CarburetorTransformation
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.tools.GeneralUtils
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
    void classDeclarations(ClassNode classNode) {

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

    @Override
    Statement createEngineDeclaration() {
        return GeneralUtils.declS(
                GeneralUtils.varX(getEngineVarName(), ClassHelper.make(TestEngine.class)),
                GeneralUtils.callX(ClassHelper.make(TestEngine.class), "getInstance")
        )
    }
}
