package ru.sbrf.cu;

public class Rectangle extends Shape {

    private double a;
    private double b;

    private double getA() { return a; }

    private void setA(double a) { this.a = a; }

    private double getB() {
        return b;
    }

    private void setB(double b) {
        this.b = b;
    }

    @Override
    public ShapeType getShapeType () {
        return ShapeType.RECTANGLE;
    }

    @Override
    public double getPerimeter() {
        return 2*(getA()+getB());
    }

    @Override
    public double getSquare() {
        return getA()*getB();
    }

    Rectangle (double a, double b) {
        setA(a);
        setB(b);
        printInfo();
    }

}
