package io.infinite.carburetor

class CarburetorConfig {

    Map<String, String> levelsByImplementingClass = [:]
    String defaultLevel = "EXPRESSION"

    CarburetorLevel getLevel(String annotationClassName) {
        return CarburetorLevel.valueOf(levelsByImplementingClass.get(annotationClassName)?:defaultLevel)
    }

}