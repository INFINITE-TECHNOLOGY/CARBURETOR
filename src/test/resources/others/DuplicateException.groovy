package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

public class DuplicateException {

    @TestCarburetor(level = CarburetorLevel.ERROR)
    void exception() {
        throw new Exception("This is test exception")
    }

    @TestCarburetor(level = CarburetorLevel.ERROR)
    void exception2() {
        exception()
    }

    void test() {
        try {
            exception2()
        } catch (Exception ignored) {

        }
    }

}
