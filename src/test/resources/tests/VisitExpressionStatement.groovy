package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitExpressionStatementNoneLevel() {
    int z
    z = z
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitExpressionStatementMethodErrorLevel() {
    int z
    z = z
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitExpressionStatementMethodLevel() {
    int z
    z = z
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitExpressionStatementStatementLevel() {
    int z
    z = z
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitExpressionStatementExpressionLevel() {
    int z
    z = z
}