package io.infinite.carburetor.ast

class MetaDataMethodNode extends MetaDataASTNode {

    String methodName
    String className
    String classSimpleName
    String packageName

    MetaDataMethodNode(
            String methodClassName,
            String methodClassPackageName,
            String methodName,
            Integer columnNumber,
            Integer lastColumnNumber,
            Integer lineNumber,
            Integer lastLineNumber
    ) {
        this.packageName = packageName
        this.classSimpleName = methodClassName
        this.className = methodClassPackageName + "." + methodClassName
        this.methodName = methodName
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
        this.sourceNodeName = sourceNodeName
    }

}
