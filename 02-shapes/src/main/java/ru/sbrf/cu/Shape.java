package ru.sbrf.cu;

import static java.lang.String.format;

public abstract class Shape {
    private ShapeType shapeType;
    private double perimeter;
    private double square;

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getSquare() {
        return square;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void printInfo() {
        System.out.println("Perimeter of "+shapeType.val+" is "+format("%.2f", getPerimeter()));
        System.out.println("Square of "+shapeType.val+" is "+format("%.2f", getSquare()));
    }

    @Override
    public String toString() {
        return shapeType.val + "(p=" + getPerimeter() + "; s=" + getSquare() + ")";
    }
}
