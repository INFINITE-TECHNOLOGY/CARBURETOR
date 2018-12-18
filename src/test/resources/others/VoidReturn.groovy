package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class VoidReturn implements Runnable {

    @Override
    //@TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void run() {
        return
    }

}