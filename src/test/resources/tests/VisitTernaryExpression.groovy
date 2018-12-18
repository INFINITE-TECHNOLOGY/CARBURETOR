package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitTernaryExpressionNoneLevel() {
    true?true:false
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitTernaryExpressionMethodErrorLevel() {
    true?true:false
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitTernaryExpressionMethodLevel() {
    true?true:false
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitTernaryExpressionStatementLevel() {
    true?true:false
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitTernaryExpressionExpressionLevel() {
    true?true:false
}
