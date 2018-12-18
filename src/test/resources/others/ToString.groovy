package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class ToString {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    @Override
    String toString() {
        return super.toString()
    }

}
