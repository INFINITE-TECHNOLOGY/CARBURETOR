package io.infinite.carburetor.tests


class MethodExpressionTest extends GroovyTestCase {


    void test() {
        assertScript("""import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class MethodLevel {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    def test(String arg1) {
        println "OK"
    }

}

new MethodLevel().test("abcd")
""")
    }


}