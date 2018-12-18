package io.infinite.carburetor.ast

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true)
abstract class MetaDataASTNode {

    String restoredScriptCode
    BigInteger lineNumber
    BigInteger columnNumber
    BigInteger lastLineNumber
    BigInteger lastColumnNumber

}
