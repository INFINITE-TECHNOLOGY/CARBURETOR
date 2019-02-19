package io.infinite.carburetor.tests

import groovy.transform.Memoized
import org.slf4j.MDC

class VariableScopeTest extends GroovyTestCase {

    GroovyClassLoader groovyClassLoader = new GroovyClassLoader()

    void test() {
        executeTests()
    }

    void executeTests() {

        def testInstance = getTestInstance("tests", "VariableScope.groovy")
        testInstance.applyPlugin()

    }

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

