package io.infinite.carburetor

import groovy.transform.CompileStatic

@CompileStatic
class CarburetorConfig {

    Map<String, String> levelsByImplementingClass = [:]
    String defaultLevel = "ERROR"

    CarburetorLevel getLevel(String annotationClassName) {
        return CarburetorLevel.valueOf(levelsByImplementingClass.get(annotationClassName) ?: defaultLevel)
    }

}