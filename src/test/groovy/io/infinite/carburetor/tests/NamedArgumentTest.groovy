package io.infinite.carburetor.tests

class NamedArgumentTest extends CaburetorTestCase {


    void test() {
        assertScript("""import io.infinite.carburetor.TestCarburetor

class NamedArgumentConstructorClass {

    def a

    @TestCarburetor
    def test() {
        return new NamedArgumentConstructorClass(a: "a")
    }

}

assert new NamedArgumentConstructorClass().test().a == "a"
""")
    }


}