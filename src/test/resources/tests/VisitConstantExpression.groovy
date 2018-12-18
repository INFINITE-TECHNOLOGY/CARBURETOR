package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitConstantExpressionNoneLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitConstantExpressionMethodErrorLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitConstantExpressionMethodLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitConstantExpressionStatementLevel() {
    System.getProperty("z")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitConstantExpressionExpressionLevel() {
    System.getProperty("z")
}