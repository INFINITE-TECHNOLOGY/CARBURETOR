package others

import io.infinite.carburetor.CarburetorLevel
import io.infinite.carburetor.TestCarburetor

class RuntimeExceptionTest {

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    void problematicMethod() {
        1/0
    }

    void runTest() {
        try {
            problematicMethod()
        } catch (Exception e) {
            assert e.stackTrace.size()==0
        }
    }

}
