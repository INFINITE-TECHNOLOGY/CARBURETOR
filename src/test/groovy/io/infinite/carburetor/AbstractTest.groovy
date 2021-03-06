package io.infinite.carburetor

import groovy.transform.Memoized
import io.infinite.carburetor.tests.CaburetorTestCase
import org.slf4j.MDC

abstract class AbstractTest extends CaburetorTestCase {

    GroovyClassLoader groovyClassLoader = new GroovyClassLoader()

    void test() {
        executeTests()
    }

    abstract void executeTests()

    @Memoized
    def getTestInstance(String sectionName, String testScriptName) {
        def testInstance = groovyClassLoader.parseClass(getTestScript(sectionName, testScriptName)).newInstance()
        MDC.put("unitName", "EXECUTION_${testScriptName}")
        return testInstance
    }

    File getTestScript(String sectionName, String testScriptName) {
        ClassLoader classLoader = getClass().getClassLoader()
        File file = new File(classLoader.getResource(sectionName + "/" + testScriptName).getFile())
        return file
    }

}

