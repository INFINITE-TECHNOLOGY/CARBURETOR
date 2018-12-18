package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitCatchStatementNoneLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitCatchStatementMethodErrorLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitCatchStatementMethodLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitCatchStatementStatementLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitCatchStatementExpressionLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}
