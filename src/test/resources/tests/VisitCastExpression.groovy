package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitCastExpressionNoneLevel() {
    (Integer) "1"
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitCastExpressionMethodErrorLevel() {
    (Integer) "1"
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitCastExpressionMethodLevel() {
    (Integer) "1"
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitCastExpressionStatementLevel() {
    (Integer) "1"
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitCastExpressionExpressionLevel() {
    (Integer) "1"
}