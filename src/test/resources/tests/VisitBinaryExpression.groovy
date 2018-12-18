package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitBinaryExpressionNoneLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitBinaryExpressionMethodErrorLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitBinaryExpressionMethodLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitBinaryExpressionStatementLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitBinaryExpressionExpressionLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}
