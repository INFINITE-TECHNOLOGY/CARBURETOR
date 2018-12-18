package io.infinite.carburetor.tests

import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel

@TestCarburetor(level = CarburetorLevel.NONE)
void visitSpreadExpressionNoneLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@TestCarburetor(level = CarburetorLevel.ERROR)
void visitSpreadExpressionMethodErrorLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@TestCarburetor(level = CarburetorLevel.METHOD)
void visitSpreadExpressionMethodLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@TestCarburetor(level = CarburetorLevel.STATEMENT)
void visitSpreadExpressionStatementLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@TestCarburetor(level = CarburetorLevel.EXPRESSION)
void visitSpreadExpressionExpressionLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}
