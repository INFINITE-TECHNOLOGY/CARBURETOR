package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitContinueStatementNoneLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitContinueStatementMethodErrorLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitContinueStatementMethodLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitContinueStatementStatementLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitContinueStatementExpressionLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}
