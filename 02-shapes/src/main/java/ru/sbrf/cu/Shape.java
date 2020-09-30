package ru.sbrf.cu;

import static java.lang.String.format;

public abstract class Shape {

    public abstract ShapeType getShapeType();

    public abstract double getPerimeter();

    public abstract double getSquare();

    public void printInfo() {
        System.out.println("Perimeter of "+getShapeType().val+" is "+format("%.2f", getPerimeter()));
        System.out.println("Square of "+getShapeType().val+" is "+format("%.2f", getSquare()));
    }

    @Override
    public String toString() {
        return getShapeType() + "(p=" + getPerimeter() + "; s=" + getSquare() + ")";
    }
}
