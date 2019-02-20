package test.package.name


import groovy.transform.CompileStatic
import io.infinite.carburetor.CarburetorLevel
import io.infinite.carburetor.TestCarburetor

@TestCarburetor(level= CarburetorLevel.ERROR)
@CompileStatic
class Foo {

    String foo

    Foo(String foo) {
        this.foo = foo
    }

}

@TestCarburetor(level= CarburetorLevel.ERROR)
@CompileStatic
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

new Foo("OK")