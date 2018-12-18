package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitMethodPointerExpressionNoneLevel() {
    this.&tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitMethodPointerExpressionMethodErrorLevel() {
    this.&tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitMethodPointerExpressionMethodLevel() {
    this.&tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitMethodPointerExpressionStatementLevel() {
    this.&tst(1,2,3)
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitMethodPointerExpressionExpressionLevel() {
    this.&tst(1,2,3)
}


void tst(def a, def b, def c) {
    System.out.println(a+b+c)
}