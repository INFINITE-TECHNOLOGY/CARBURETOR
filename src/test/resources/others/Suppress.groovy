package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

public class Suppress {

    void exception() {
        throw new Exception("This is test exception")
    }

    @TestCarburetor(level = CarburetorLevel.EXPRESSION, suppressExceptions = true)
    void test() {
        exception()
    }

}
