package ru.sbrf.cu;

public class Square extends Shape {

    private double a;

    private double getA() { return a; }

    private void setA(double a) { this.a = a; }

    @Override
    public ShapeType getShapeType () { return ShapeType.SQUARE; }

    @Override
    public double getPerimeter() { return 4*(getA()); }

    @Override
    public double getSquare() {
        return getA()*getA();
    }

    Square(double a) {
        setA(a);
        printInfo();
    }

}
