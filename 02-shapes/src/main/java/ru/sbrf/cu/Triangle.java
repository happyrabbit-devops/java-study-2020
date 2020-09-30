package ru.sbrf.cu;

public class Triangle extends Shape {

    private double a;
    private double b;
    private double c;

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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public ShapeType getShapeType () {
        return ShapeType.TRIANGLE;
    }

    @Override
    public double getPerimeter() {
        return getA()+getB()+getC();
    }

    @Override
    public double getSquare() {
        double a = getA();
        double b = getB();
        double c = getC();
        double s = -1.0;
        if ((a<(c+b)) && (b<(a+c)) && (c<(a+b))) {
            double p = (a+b+c)/2;
            s = Math.sqrt((p*(p-a)*(p-b)*(p-c)));
        }
        return s;
    }

    public Triangle(double a, double b, double c) {
        setA(a);
        setB(b);
        setC(c);
        printInfo();
    }

}
