package io.infinite.carburetor

import io.infinite.carburetor.ast.MetaDataASTNode

class CarburetorRuntimeException extends Exception{

    CarburetorRuntimeException(MetaDataASTNode metaDataASTNode, Exception exception) {
        super(metaDataASTNode.toString(), exception)
        this.setStackTrace([] as StackTraceElement[])
    }

}
