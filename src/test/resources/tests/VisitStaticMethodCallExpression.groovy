package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

static String tst(String z, String q, String w) {
    return z+q+w
}

@TestCarburetor(level = CarburetorLevel.NONE)
void visitStaticMethodCallExpressionNoneLevel() {
    tst("1","2","3")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitStaticMethodCallExpressionMethodErrorLevel() {
    tst("1","2","3")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitStaticMethodCallExpressionMethodLevel() {
    tst("1","2","3")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitStaticMethodCallExpressionStatementLevel() {
    tst("1","2","3")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitStaticMethodCallExpressionExpressionLevel() {
    tst("1","2","3")
//    1/0
}