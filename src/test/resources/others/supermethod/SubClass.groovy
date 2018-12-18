package io.infinite.carburetor.others.supermethod

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class SubClass extends SuperClass implements Runnable {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    String bar(String bar) {
        super.bar("bar")
    }

    @Override
    void run() {
        bar()
    }
}
