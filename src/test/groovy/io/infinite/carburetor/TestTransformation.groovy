package io.infinite.carburetor

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import jdk.internal.org.objectweb.asm.Opcodes
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
        if (classNode.getDeclaredField("optionalField") == null) {
            classNode.addField("optionalField",
                    Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                    ClassHelper.make(Logger.class),
                    GeneralUtils.callX(
                            new ClassExpression(ClassHelper.make(LoggerFactory.class)),
                            "getLogger",
                            GeneralUtils.constX(classNode.getName())
                    ))
        }
    }

    @Override
    Class getEngineFactoryClass() {
        return TestEngine.class
    }

    @Override
    Expression getEngineInitArgs() {
        GeneralUtils.args(GeneralUtils.fieldX(methodNode.declaringClass, "optionalField"))
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
