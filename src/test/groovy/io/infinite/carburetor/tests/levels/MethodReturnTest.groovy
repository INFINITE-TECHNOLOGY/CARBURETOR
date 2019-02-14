package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorTransformation

class MethodReturnTest extends GroovyTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    @TestCarburetor(level = CarburetorLevel.METHOD)
    def test(String bar) {
        bar
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
testEngine.methodExecutionOpen(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'Foo', 'test'), ['bar': bar ])
try {
    testEngine.executeMethod({ java.lang.Object itVariableReplacement0 ->
        bar 
    }, thisInstance)
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