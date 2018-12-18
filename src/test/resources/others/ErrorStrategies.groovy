package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class ErrorStrategies {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION, suppressExceptions = true)
    void test() {
        test2()
    }

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void test2() {
        test3()
    }

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void test3() {
        throw new Exception("Test exception")
    }

}
