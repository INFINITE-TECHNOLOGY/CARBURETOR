package io.infinite.carburetor

enum CarburetorLevel {

    EXPRESSION(400),
    STATEMENT(300),
    METHOD(200),
    ERROR(100),
    NONE(0)

    private final int carburetorLevel

    CarburetorLevel(int carburetorLevel) {
        this.carburetorLevel = carburetorLevel
    }

    int value() {
        return carburetorLevel
    }

}