package io.infinite.carburetor

import io.infinite.carburetor.CarburetorLevel
import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target([ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE])
@Retention(RetentionPolicy.RUNTIME)
@GroovyASTTransformationClass("io.infinite.carburetor.TestTransformation")
@interface TestCarburetor {

    CarburetorLevel level() default CarburetorLevel.METHOD

}