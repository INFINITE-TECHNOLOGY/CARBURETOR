package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitConstructorCallExpressionNoneLevel() {
    Object object = new Object()
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitConstructorCallExpressionMethodErrorLevel() {
    Object object = new Object()
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitConstructorCallExpressionMethodLevel() {
    Object object = new Object()
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitConstructorCallExpressionStatementLevel() {
    Object object = new Object()
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitConstructorCallExpressionExpressionLevel() {
    Object object = new Object()
}
