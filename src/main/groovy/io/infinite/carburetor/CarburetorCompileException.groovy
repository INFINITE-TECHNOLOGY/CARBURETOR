package io.infinite.carburetor

import io.infinite.carburetor.ast.MetaDataASTNode
import io.infinite.carburetor.ast.MetaDataExpression
import io.infinite.carburetor.ast.MetaDataMethodNode
import io.infinite.carburetor.ast.MetaDataStatement
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.stmt.Statement

class CarburetorCompileException extends Exception {
    CarburetorCompileException(ASTNode astNode, Exception exception, CarburetorTransformation carburetorTransformation) {
        super(getMessage(astNode, carburetorTransformation), exception)
        this.setStackTrace([] as StackTraceElement[])
    }

    CarburetorCompileException(ASTNode astNode, String message, CarburetorTransformation carburetorTransformation) {
        super(message +"\n" + getMessage(astNode, carburetorTransformation))
        this.setStackTrace([] as StackTraceElement[])
    }

    static String getMessage(ASTNode astNode, CarburetorTransformation carburetorTransformation) {
        String message
        switch (astNode) {
            case MethodNode:
                message = new MetaDataMethodNode(
                        ((MethodNode) astNode).getDeclaringClass().getNameWithoutPackage(),
                        ((MethodNode) astNode).getDeclaringClass().getPackageName(),
                        ((MethodNode) astNode).getName(),
                        ((MethodNode) astNode).getColumnNumber(),
                        ((MethodNode) astNode).getLastColumnNumber(),
                        ((MethodNode) astNode).getLineNumber(),
                        ((MethodNode) astNode).getLastLineNumber()
                ).toString()
                break
            case Statement:
                message = new MetaDataStatement(
                        ((Statement) astNode).getClass().getSimpleName(),
                        carburetorTransformation.codeString(astNode),
                        ((Statement) astNode).getColumnNumber(),
                        ((Statement) astNode).getLastColumnNumber(),
                        ((Statement) astNode).getLineNumber(),
                        ((Statement) astNode).getLastLineNumber(),
                        null
                ).toString()
                break
            case Expression:
                message = new MetaDataExpression(
                        ((Expression) astNode).getClass().getSimpleName(),
                        carburetorTransformation.codeString(astNode),
                        ((Expression) astNode).getColumnNumber(),
                        ((Expression) astNode).getLastColumnNumber(),
                        ((Expression) astNode).getLineNumber(),
                        ((Expression) astNode).getLastLineNumber(),
                        null
                ).toString()
                break
            default:
                message = astNode.toString()
        }
        return message
    }

}
