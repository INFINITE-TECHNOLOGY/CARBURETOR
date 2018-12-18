package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitGStringExpressionNoneLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitGStringExpressionMethodErrorLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitGStringExpressionMethodLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitGStringExpressionStatementLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitGStringExpressionExpressionLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}