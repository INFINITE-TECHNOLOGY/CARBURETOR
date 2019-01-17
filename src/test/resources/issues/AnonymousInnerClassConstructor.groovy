package issues

import io.infinite.carburetor.CarburetorLevel
import io.infinite.carburetor.TestCarburetor

class IteratorTest3<Type> extends ArrayList<Type> {

    private Integer index = 0

    @TestCarburetor(level = CarburetorLevel.EXPRESSION)
    @Override
    Iterator<Type> iterator() {
        return new Iterator<Type>() {

            @Override
            boolean hasNext() {
                return true
            }

            @Override
            Type next() {
                return null
            }

            @Override
            void remove() {
                throw new Exception("Unable to remove from RoundRobin")
            }

        }
    }

    void runTest() {
        ++iterator()
        println("success")
    }

}