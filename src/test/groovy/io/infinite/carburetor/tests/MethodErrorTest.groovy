package io.infinite.carburetor.tests


class MethodErrorTest extends CaburetorTestCase {


    void test() {
        assertScript("""import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class MethodLevel {

    @TestCarburetor(level = CarburetorLevel.ERROR)
    def test(String arg1) {
        println "OK"
    }

}

new MethodLevel().test("abcd")
""")
    }


}