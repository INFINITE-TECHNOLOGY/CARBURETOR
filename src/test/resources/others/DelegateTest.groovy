package io.infinite.carburetor.others

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

class DelegateTest {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void testBlackBox(Object iThis) {
        Closure c = {
            assert delegate == this
            assert owner == this
            assert thisObject == this
            assert delegate == iThis
            assert owner == iThis
            assert thisObject == iThis
            assert iThis == this
            assert iThis == this
            assert iThis == this
        }
        c()
    }

    void test() {
        testBlackBox(this)
    }

}
