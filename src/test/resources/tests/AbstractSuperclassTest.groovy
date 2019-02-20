package tests

import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.carburetor.CarburetorLevel
import io.infinite.carburetor.TestCarburetor

@TestCarburetor(level= CarburetorLevel.ERROR)
@CompileStatic
@ToString(includeNames = true, includeFields = true)
abstract class Foo {

    String foo

    Foo(String foo) {
        this.foo = foo
    }

    abstract void foo()

}

@TestCarburetor(level= CarburetorLevel.ERROR)
@CompileStatic
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class Bar extends Foo {


    Bar(String foo) {
        super(foo)
    }

    @Override
    void foo() {

    }
}

new Bar("OK")