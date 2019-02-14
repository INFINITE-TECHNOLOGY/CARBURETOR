package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorTransformation

class ErrorTest extends GroovyTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    @TestCarburetor(level = CarburetorLevel.ERROR)
    def test(String bar) {
        bar
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
try {
    bar 
} 
catch (java.lang.Exception automaticException) {
    testEngine.methodExecutionException(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'Foo', 'test'), ['bar': bar ])
    testEngine.exception(automaticException)
    testEngine.executionClose()
    throw automaticException 
} 
finally { 
} 
"""


}