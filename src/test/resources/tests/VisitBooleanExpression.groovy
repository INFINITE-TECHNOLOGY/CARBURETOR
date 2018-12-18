package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitBooleanExpressionNoneLevel() {
    if (true) false
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitBooleanExpressionMethodErrorLevel() {
    if (true) false
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitBooleanExpressionMethodLevel() {
    if (true) false
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitBooleanExpressionStatementLevel() {
    if (true) false
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitBooleanExpressionExpressionLevel() {
    if (true) false
}