package test.package.name


import groovy.transform.CompileStatic
import io.infinite.carburetor.TestCarburetor

//@CompileStatic
@TestCarburetor
class Foo {

    String foo

    Foo(String foo) {
        this.foo = foo
    }

}

//@CompileStatic
@TestCarburetor
class Bar extends Foo {

    String bar

    Bar(String foo) {
        super(foo)
        this.bar = foo
        bar = foo
        println this.bar
        println bar
    }

}

new Bar("OK")