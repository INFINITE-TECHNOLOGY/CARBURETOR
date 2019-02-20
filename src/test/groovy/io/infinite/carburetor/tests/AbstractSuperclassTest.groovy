package io.infinite.carburetor.tests

import io.infinite.carburetor.AbstractTest

class AbstractSuperclassTest extends AbstractTest {

    @Override
    void executeTests() {
        def testInstance = getTestInstance("tests", "AbstractSuperclassTest.groovy")
        testInstance.run()
    }

}