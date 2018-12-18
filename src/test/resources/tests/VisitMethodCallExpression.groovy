package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitMethodCallExpressionNoneLevel() {
    System.out.println("test")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitMethodCallExpressionMethodErrorLevel() {
    System.out.println("test")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitMethodCallExpressionMethodLevel() {
    System.out.println("test")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitMethodCallExpressionStatementLevel() {
    System.out.println("test")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitMethodCallExpressionExpressionLevel() {
    System.out.println("test")
}