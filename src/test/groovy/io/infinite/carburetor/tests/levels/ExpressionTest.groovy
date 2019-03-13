package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorEngine
import io.infinite.carburetor.CarburetorTransformation
import io.infinite.carburetor.TestEngine
import io.infinite.carburetor.tests.CaburetorTestCase


class ExpressionTest extends CaburetorTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void test(String bar2) {
        def foo = bar()
    }
    
    void bar() {
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
testEngine.methodBegin(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'test', 'Foo'), ['bar2': bar2 ])
try {
    testEngine.expressionBegin(new io.infinite.supplies.ast.metadata.MetaDataExpression('DeclarationExpression', 'java.lang.Object foo = this.bar()', 10, 10, 9, 24, 'test', 'Foo'))java.lang.Object foo = testEngine.expressionEvaluation(new io.infinite.supplies.ast.metadata.MetaDataExpression('MethodCallExpression', 'this.bar()', 10, 10, 19, 24, 'test', 'Foo'), { java.lang.Object itVariableReplacement1 ->
        return testEngine.expressionEvaluation(new io.infinite.supplies.ast.metadata.MetaDataExpression('VariableExpression', 'this ', -1, -1, -1, -1, 'test', 'Foo'), { java.lang.Object itVariableReplacement0 ->
            return this 
        }, thisInstance).bar()
    }, thisInstance)testEngine.expressionEnd(new io.infinite.supplies.ast.metadata.MetaDataExpression('DeclarationExpression', 'java.lang.Object foo = this.bar()', 10, 10, 9, 24, 'test', 'Foo'))
} 
catch (java.lang.Exception automaticException) {
    testEngine.methodException(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'test', 'Foo'), ['bar2': bar2 ], automaticException)
    throw automaticException 
} 
finally { 
    testEngine.methodEnd(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(8, 11, 5, 6, 'test', 'Foo'))} 
"""


}