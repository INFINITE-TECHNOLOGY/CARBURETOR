package io.infinite.carburetor.tests

import io.infinite.carburetor.CarburetorTransformation

class StaticMethodTest extends CaburetorTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    static void test(String bar) {
        if (true) {
            def foo
        }
    }

}

new Foo().test()
""")
    }

}