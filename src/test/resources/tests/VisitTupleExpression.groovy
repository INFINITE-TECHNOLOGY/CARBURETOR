package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitTupleExpressionNoneLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitTupleExpressionMethodErrorLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitTupleExpressionMethodLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitTupleExpressionStatementLevel() {
    def (int a, int b) = [1,2]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitTupleExpressionExpressionLevel() {
    def (int a, int b) = [1,2]
}