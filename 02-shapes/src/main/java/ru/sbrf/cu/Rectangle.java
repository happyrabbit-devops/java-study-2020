package ru.sbrf.cu;

public class Rectangle extends Shape {

    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public void setA(double a) { this.a = a; }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public ShapeType getShapeType () {
        return ShapeType.RECTANGLE;
    }

    @Override
    public double getPerimeter() {
        return 2*(this.a+this.b);
    }

    @Override
    public double getSquare() {
        return this.a*this.b;
    }

    public Rectangle(double a, double b) {
        setA(a);
        setB(b);
        printInfo();
    }

}
