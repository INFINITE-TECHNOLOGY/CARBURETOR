package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitMapExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitMapExpressionMethodErrorLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitMapExpressionMethodLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitMapExpressionStatementLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitMapExpressionExpressionLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

@TestCarburetor(level = CarburetorLevel.NONE)
void visitMapEntryExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}