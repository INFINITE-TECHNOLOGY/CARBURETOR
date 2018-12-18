package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitEmptyStatementNoneLevel() {
    //skipped
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitEmptyStatementMethodErrorLevel() {
    //skipped
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitEmptyStatementMethodLevel() {
    //skipped
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitEmptyStatementStatementLevel() {
    //skipped
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitEmptyStatementExpressionLevel() {
    //skipped
}