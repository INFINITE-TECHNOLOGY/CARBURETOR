package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorTransformation
import io.infinite.carburetor.tests.CaburetorTestCase

class StatementControlTest extends CaburetorTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    void foo() {}

    @TestCarburetor(level = CarburetorLevel.STATEMENT)
    def test(String bar) {
        return bar
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
testEngine.methodBegin(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(10, 13, 5, 6, 'test', 'Foo'), ['bar': bar ])
try {
    testEngine.executeMethod({ java.lang.Object itVariableReplacement0 ->
        testEngine.preprocessControlStatement(new io.infinite.supplies.ast.metadata.MetaDataStatement('ReturnStatement', 12, 12, 9, 19, 'test', 'Foo'))
        return bar 
    }, thisInstance)
} 
catch (java.lang.Exception automaticException) {
    testEngine.methodException(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(10, 13, 5, 6, 'test', 'Foo'), ['bar': bar ], automaticException)
    throw automaticException 
} 
finally { 
    testEngine.methodEnd(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(10, 13, 5, 6, 'test', 'Foo'))} 
"""

}