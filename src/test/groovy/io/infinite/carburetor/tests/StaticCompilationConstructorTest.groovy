package io.infinite.carburetor.tests

import io.infinite.carburetor.AbstractTest

class StaticCompilationConstructorTest extends AbstractTest {

    @Override
    void executeTests() {
        def testInstance = getTestInstance("tests", "StaticCompilationConstructorTest.groovy")
        testInstance.run()
    }

}