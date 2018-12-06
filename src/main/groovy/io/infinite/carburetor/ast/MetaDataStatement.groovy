package io.infinite.carburetor.ast

class MetaDataStatement extends MetaDataASTNode {

    String statementClassName

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
