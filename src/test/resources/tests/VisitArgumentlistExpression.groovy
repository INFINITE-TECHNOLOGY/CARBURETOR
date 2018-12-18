package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitArgumentlistExpressionNoneLevel() {
    tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitArgumentlistExpressionMethodErrorLevel() {
    tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitArgumentlistExpressionMethodLevel() {
    tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitArgumentlistExpressionStatementLevel() {
    tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitArgumentlistExpressionExpressionLevel() {
    tst(1,2,3)
}

void tst(def a, def b, def c) {
    System.out.println(a+b+c)
}