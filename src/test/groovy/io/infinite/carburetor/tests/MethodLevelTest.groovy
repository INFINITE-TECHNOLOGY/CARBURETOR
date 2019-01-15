package io.infinite.carburetor.tests


class MethodLevelTest extends GroovyTestCase {


    void test() {
        assertScript("""import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class MethodLevel {

    @TestCarburetor(level = CarburetorLevel.METHOD)
    def test() {
        println "OK"
    }

}

new MethodLevel().test()
""")
    }


}