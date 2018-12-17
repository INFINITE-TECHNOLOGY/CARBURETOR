package io.infinite.carburetor.ast

class MetaDataExpression extends MetaDataASTNode {

    String expressionClassName
    String sourceNodeName

    MetaDataExpression(
        String expressionClassName,
        String origCodeString,
        Integer columnNumber,
        Integer lastColumnNumber,
        Integer lineNumber,
        Integer lastLineNumber,
        String sourceNodeName
    ) {
        this.expressionClassName = expressionClassName
        this.restoredScriptCode = origCodeString
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
        this.sourceNodeName = sourceNodeName
    }

}
