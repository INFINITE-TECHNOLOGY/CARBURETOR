package io.infinite.carburetor.tests


class ElvisTest extends CaburetorTestCase {


    void test() {
        assertScript("""import io.infinite.carburetor.TestCarburetor

class Elvis {


    static class InnerClass {
        Map map = [:]
    }

    InnerClass innerClass = new InnerClass()

    @TestCarburetor
    def test() {
        innerClass.properties.get("fileKey") ?: "\\"default\\""
    }

}

new Elvis().test()
""")
    }


}