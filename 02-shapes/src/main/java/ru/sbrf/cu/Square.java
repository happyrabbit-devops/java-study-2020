package ru.sbrf.cu;

public class Square extends Rectangle {

    @Override
    public ShapeType getShapeType () {
        return ShapeType.SQUARE;
    }

    public Square(double a, double b) {
        super(a, b);
    }

}
