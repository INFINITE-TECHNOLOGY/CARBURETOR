package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitMapEntryExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitMapEntryExpressionMethodErrorLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitMapEntryExpressionMethodLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitMapEntryExpressionStatementLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitMapEntryExpressionExpressionLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}
