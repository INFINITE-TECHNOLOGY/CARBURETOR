package io.infinite.carburetor

import groovy.transform.CompileStatic
import io.infinite.supplies.ast.exceptions.RuntimeException
import io.infinite.supplies.ast.metadata.MetaDataASTNode

@CompileStatic
class CarburetorRuntimeException extends RuntimeException {


    CarburetorRuntimeException(String var1) {
        super(var1)
    }

    CarburetorRuntimeException(String var1, Exception exception) {
        super(var1, exception)
    }

    CarburetorRuntimeException(MetaDataASTNode metaDataASTNode, Exception exception) {
        super(metaDataASTNode, exception)
    }
}
