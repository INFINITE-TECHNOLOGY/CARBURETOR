package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitClassExpressionNoneLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitClassExpressionMethodErrorLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitClassExpressionMethodLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitClassExpressionStatementLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitClassExpressionExpressionLevel() {
    System.getProperty("z")
}
