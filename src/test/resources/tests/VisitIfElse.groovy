package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitIfElseNoneLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitIfElseMethodErrorLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitIfElseMethodLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitIfElseStatementLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitIfElseExpressionLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}