package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class ItVariable implements Runnable{

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void test() {
        Object[] objects = [1,2,3,4,5]
        objects.each {
            assert it != null
        }
    }

    @Override
    void run() {
        test()
    }
}
