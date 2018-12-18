package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


@TestCarburetor(level = CarburetorLevel.NONE)
void visitWhileLoopNoneLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitWhileLoopMethodErrorLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitWhileLoopMethodLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitWhileLoopStatementLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitWhileLoopExpressionLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}