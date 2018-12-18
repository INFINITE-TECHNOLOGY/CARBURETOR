package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitBlockStatementNoneLevel() {
    System.out.println("Test")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitBlockStatementMethodErrorLevel() {
    System.out.println("Test")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitBlockStatementMethodLevel() {
    System.out.println("Test")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitBlockStatementStatementLevel() {
    System.out.println("Test")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitBlockStatementExpressionLevel() {
    System.out.println("Test")
}