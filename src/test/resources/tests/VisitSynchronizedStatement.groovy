package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitSynchronizedStatementNoneLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitSynchronizedStatementMethodErrorLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitSynchronizedStatementMethodLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitSynchronizedStatementStatementLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitSynchronizedStatementExpressionLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}