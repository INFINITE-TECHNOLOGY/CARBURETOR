package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitDoWhileLoopNoneLevel() {
    //unsupported by Groovy now
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitDoWhileLoopMethodErrorLevel() {
    //unsupported by Groovy now
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitDoWhileLoopMethodLevel() {
    //unsupported by Groovy now
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitDoWhileLoopStatementLevel() {
    //unsupported by Groovy now
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitDoWhileLoopExpressionLevel() {
    //unsupported by Groovy now
}