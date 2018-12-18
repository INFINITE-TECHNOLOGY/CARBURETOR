package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitShortTernaryExpressionNoneLevel() {
    def location = true ?: "z"
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitShortTernaryExpressionMethodErrorLevel() {
    def location = true ?: "z"
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitShortTernaryExpressionMethodLevel() {
    def location = true ?: "z"
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitShortTernaryExpressionStatementLevel() {
    def location = true ?: "z"
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitShortTernaryExpressionExpressionLevel() {
    def location = true ?: "z"
}
