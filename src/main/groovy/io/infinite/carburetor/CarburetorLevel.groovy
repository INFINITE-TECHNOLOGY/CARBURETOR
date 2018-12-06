package io.infinite.carburetor

enum CarburetorLevel {

    EXPRESSION(300),
    STATEMENT(200),
    METHOD(100),
    NONE(0)

    private final int carburetorLevel

    CarburetorLevel(int carburetorLevel) {
        this.carburetorLevel = carburetorLevel
    }

    int value() {
        return carburetorLevel
    }

}