package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitDeclarationExpressionNoneLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitDeclarationExpressionMethodErrorLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitDeclarationExpressionMethodLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitDeclarationExpressionStatementLevel() {
    def z = 1
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitDeclarationExpressionExpressionLevel() {
    def z = 1
}
