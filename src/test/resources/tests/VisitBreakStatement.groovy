package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitBreakStatementNoneLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitBreakStatementMethodErrorLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitBreakStatementMethodLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitBreakStatementStatementLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitBreakStatementExpressionLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}