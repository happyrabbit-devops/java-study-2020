package ru.sbrf.cu;

public class Rectangle extends Shape {

    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    private void calcPerimeter() {
        setPerimeter(2*(this.a+this.b));
    }

    private void calcSquare() {
        setSquare(this.a*this.b);
    }

    public Rectangle(double a, double b) {
        setShapeType(ShapeType.RECTANGLE);
        setA(a);
        setB(b);
        calcPerimeter();
        calcSquare();
        printInfo();
    }

    public Rectangle(double a, double b, ShapeType type) {
        setShapeType(type);
        setA(a);
        setB(b);
        calcPerimeter();
        calcSquare();
        printInfo();
    }

}
