package io.infinite.carburetor.ast

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataStatement extends MetaDataASTNode {

    String statementClassName
    String sourceNodeName

    MetaDataStatement(
            String statementClassName,
            String origCodeString,
            Integer columnNumber,
            Integer lastColumnNumber,
            Integer lineNumber,
            Integer lastLineNumber,
            String sourceNodeName
    ) {
        this.statementClassName = statementClassName
        this.restoredScriptCode = origCodeString
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
        this.sourceNodeName = sourceNodeName
    }

}
