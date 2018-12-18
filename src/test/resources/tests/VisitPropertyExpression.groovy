package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitPropertyExpressionNoneLevel() {
    System.out
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitPropertyExpressionMethodErrorLevel() {
    System.out
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitPropertyExpressionMethodLevel() {
    System.out
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitPropertyExpressionStatementLevel() {
    System.out
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitPropertyExpressionExpressionLevel() {
    System.out
}
