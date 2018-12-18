package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class ThreadSafety extends Thread{

    @Override
    void run() {
        runWithLogging()
    }

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void runWithLogging() {
        [1..5].each {
            sleep(10)
        }
    }

}
