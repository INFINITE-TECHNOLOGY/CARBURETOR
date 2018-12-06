package io.infinite.carburetor

import org.codehaus.groovy.GroovyBugError

class CarburetorException extends Exception{
    CarburetorException(String message) {
        super(message)
    }
}
