package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitVariableExpressionNoneLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitVariableExpressionMethodErrorLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitVariableExpressionMethodLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitVariableExpressionStatementLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitVariableExpressionExpressionLevel() {
    def z = 1
}
