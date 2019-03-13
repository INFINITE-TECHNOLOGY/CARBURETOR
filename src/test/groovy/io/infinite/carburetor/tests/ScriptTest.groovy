package io.infinite.carburetor.tests

import org.testng.annotations.Test
import org.springframework.util.ClassUtils
import org.testng.annotations.Test

class ScriptTest {

    @Test
    void test() {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(ClassUtils.getClassLoader())
        Class appClass = groovyClassLoader.parseClass("""
package conf.plugins.output

import io.infinite.carburetor.CarburetorLevel
import io.infinite.carburetor.TestCarburetor

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void runWithLogging() {
    def foo = bar()
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
static void staticMethod() {
    println "STATIC TEST OK"
}

void bar() {
    println "SCRIPT TEST OK"
}

runWithLogging()
staticMethod()
""")
        appClass.newInstance().run()
        appClass.staticMethod()
    }

}