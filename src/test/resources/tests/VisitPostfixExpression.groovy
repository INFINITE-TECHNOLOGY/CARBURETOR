package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitPostfixExpressionNoneLevel() {
    int z = 1
    z++
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitPostfixExpressionMethodErrorLevel() {
    int z = 1
    z++
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitPostfixExpressionMethodLevel() {
    int z = 1
    z++
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitPostfixExpressionStatementLevel() {
    int z = 1
    z++
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitPostfixExpressionExpressionLevel() {
    int z = 1
    z++
}