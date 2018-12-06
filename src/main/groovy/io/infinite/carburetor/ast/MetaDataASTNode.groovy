package io.infinite.carburetor.ast

abstract class MetaDataASTNode {

    String restoredScriptCode
    String sourceNodeName
    BigInteger lineNumber
    BigInteger columnNumber
    BigInteger lastLineNumber
    BigInteger lastColumnNumber

}
