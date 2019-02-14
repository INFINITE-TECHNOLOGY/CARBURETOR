package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorTransformation

class ExpressionTest extends GroovyTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void test(String bar) {
        def foo
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
io.infinite.carburetor.TestEngine testEngine = io.infinite.carburetor.TestEngine.getInstance()
Foo automaticThis = this 
testEngine.methodExecutionOpen(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'Foo', 'test'), ['bar': bar ])
try {
    testEngine.expressionExecutionOpen(new io.infinite.supplies.ast.metadata.MetaDataExpression('DeclarationExpression', 'java.lang.Object foo ', 10, 10, 9, 16, 'test', 'Foo'))java.lang.Object foo testEngine.executionClose()
} 
catch (java.lang.Exception automaticException) {
    testEngine.methodExecutionException(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'Foo', 'test'), ['bar': bar ])
    testEngine.exception(automaticException)
    throw automaticException 
} 
finally { 
    testEngine.executionClose()} 
"""


}