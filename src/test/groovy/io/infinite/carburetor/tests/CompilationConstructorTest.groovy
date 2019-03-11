package io.infinite.carburetor.tests

import io.infinite.carburetor.AbstractTest

class CompilationConstructorTest extends AbstractTest {

    @Override
    void executeTests() {
        def testInstance = getTestInstance("tests", "CompilationConstructorTest.groovy")
        testInstance.run()
    }

}