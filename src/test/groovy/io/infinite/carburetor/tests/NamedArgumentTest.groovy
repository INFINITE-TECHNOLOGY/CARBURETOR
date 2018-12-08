package io.infinite.carburetor.tests

class NamedArgumentTest extends GroovyTestCase {


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