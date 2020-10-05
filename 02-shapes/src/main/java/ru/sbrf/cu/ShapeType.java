package ru.sbrf.cu;

public enum ShapeType {

    TRIANGLE("triangle"),
    SQUARE("square"),
    RECTANGLE("rectangle");

    String val;

    ShapeType(String val) {
        this.val = val;
    }
}
