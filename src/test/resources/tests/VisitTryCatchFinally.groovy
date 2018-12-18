package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitTryCatchFinallyNoneLevel() {
    int wwww = 0
    while (wwww < 3) {
        try {
            System.out.println("try")
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        } catch (Throwable z) {
            System.out.println("Catched " + z)
            System.out.println("Catch")
        } finally {
            System.out.println("Finally1 " + wwww)
            System.out.println("Finally2 " + wwww)
        }
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitTryCatchFinallyMethodErrorLevel() {
    int wwww = 0
    while (wwww < 3) {
        try {
            System.out.println("try")
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        } catch (Throwable z) {
            System.out.println("Catched " + z)
            System.out.println("Catch")
        } finally {
            System.out.println("Finally1 " + wwww)
            System.out.println("Finally2 " + wwww)
        }
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitTryCatchFinallyMethodLevel() {
    int wwww = 0
    while (wwww < 3) {
        try {
            System.out.println("try")
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        } catch (Throwable z) {
            System.out.println("Catched " + z)
            System.out.println("Catch")
        } finally {
            System.out.println("Finally1 " + wwww)
            System.out.println("Finally2 " + wwww)
        }
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitTryCatchFinallyStatementLevel() {
    int wwww = 0
    while (wwww < 3) {
        try {
            System.out.println("try")
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        } catch (Throwable z) {
            System.out.println("Catched " + z)
            System.out.println("Catch")
        } finally {
            System.out.println("Finally1 " + wwww)
            System.out.println("Finally2 " + wwww)
        }
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitTryCatchFinallyExpressionLevel() {
    int wwww = 0
    while (wwww < 3) {
        try {
            System.out.println("try")
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        } catch (Throwable z) {
            System.out.println("Catched " + z)
            System.out.println("Catch")
        } finally {
            System.out.println("Finally1 " + wwww)
            System.out.println("Finally2 " + wwww)
        }
    }
}
