package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitNotExpressionNoneLevel() {
    if (!true) false
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitNotExpressionMethodErrorLevel() {
    if (!true) false
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitNotExpressionMethodLevel() {
    if (!true) false
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitNotExpressionStatementLevel() {
    if (!true) false
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitNotExpressionExpressionLevel() {
    if (!true) false
}
