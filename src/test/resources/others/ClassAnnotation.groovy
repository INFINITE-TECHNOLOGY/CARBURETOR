package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
class ClassAnnotation {

    void someMethod() {
        anotherMethod()
    }

    void anotherMethod() {

    }
}
