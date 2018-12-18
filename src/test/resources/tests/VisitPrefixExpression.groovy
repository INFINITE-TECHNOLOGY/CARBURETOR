package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitPrefixExpressionNoneLevel() {
    int z = 1
    ++z
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitPrefixExpressionMethodErrorLevel() {
    int z = 1
    ++z
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitPrefixExpressionMethodLevel() {
    int z = 1
    ++z
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitPrefixExpressionStatementLevel() {
    int z = 1
    ++z
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitPrefixExpressionExpressionLevel() {
    int z = 1
    ++z
}
