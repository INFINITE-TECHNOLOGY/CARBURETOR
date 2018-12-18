package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
String visitReturnStatementNoneLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
String visitReturnStatementMethodErrorLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
String visitReturnStatementMethodLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
String visitReturnStatementStatementLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
String visitReturnStatementExpressionLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}
