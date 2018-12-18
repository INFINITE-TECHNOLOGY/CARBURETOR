package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitRangeExpressionNoneLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitRangeExpressionMethodErrorLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitRangeExpressionMethodLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitRangeExpressionStatementLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitRangeExpressionExpressionLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}
