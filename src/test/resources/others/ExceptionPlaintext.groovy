package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class ExceptionPlaintext {

    @TestCarburetor(level = CarburetorLevel.ERROR, suppressExceptions = true)
    void test() {
        throw new Exception("Test throwable")
    }

}
