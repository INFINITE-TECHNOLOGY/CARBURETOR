package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitThrowStatementNoneLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitThrowStatementMethodErrorLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitThrowStatementMethodLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitThrowStatementStatementLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitThrowStatementExpressionLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}