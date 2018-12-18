package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitArrayExpressionNoneLevel() {
    def strArray = new String[3]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitArrayExpressionMethodErrorLevel() {
    def strArray = new String[3]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitArrayExpressionMethodLevel() {
    def strArray = new String[3]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitArrayExpressionStatementLevel() {
    def strArray = new String[3]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitArrayExpressionExpressionLevel() {
    def strArray = new String[3]
}