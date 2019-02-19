package io.infinite.carburetor

class CarburetorConfig {

    Map<String, String> levelsByImplementingClass = [:]
    String defaultLevel = "ERROR"

    CarburetorLevel getLevel(String annotationClassName) {
        return CarburetorLevel.valueOf(levelsByImplementingClass.get(annotationClassName) ?: defaultLevel)
    }

}