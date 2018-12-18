package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitAssertStatementNoneLevel() {
    assert 1==1
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitAssertStatementMethodErrorLevel() {
    assert 1==1
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitAssertStatementMethodLevel() {
    assert 1==1
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitAssertStatementStatementLevel() {
    assert 1==1
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitAssertStatementExpressionLevel() {
    assert 1==1
}