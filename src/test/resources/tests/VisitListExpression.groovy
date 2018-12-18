package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitListExpressionNoneLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitListExpressionMethodErrorLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitListExpressionMethodLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitListExpressionStatementLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitListExpressionExpressionLevel() {
    def strArray = new String[3]
}
