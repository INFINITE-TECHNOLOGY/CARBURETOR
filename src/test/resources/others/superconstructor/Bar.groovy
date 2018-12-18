package io.infinite.carburetor.others.superconstructor

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class Bar extends Foo {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    Bar(String foo) {
        super("test")
        def z = "q"
        System.out.println("test")
    }

}
