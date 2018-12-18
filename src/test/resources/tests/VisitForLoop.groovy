package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitForLoopNoneLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitForLoopMethodErrorLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitForLoopMethodLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitForLoopStatementLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitForLoopExpressionLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}
