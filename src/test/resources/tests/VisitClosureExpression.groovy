package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitClosureExpressionNoneLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitClosureExpressionMethodErrorLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitClosureExpressionMethodLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitClosureExpressionStatementLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitClosureExpressionExpressionLevel() {
    Closure c = {
        System.out.println("z")
    }
}
