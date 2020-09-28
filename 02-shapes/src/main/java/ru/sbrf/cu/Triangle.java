package ru.sbrf.cu;

public class Triangle extends Shape {

    private double a;
    private double b;
    private double c;

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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    private void calcPerimeter() {
        setPerimeter(this.a+this.b+this.c);
    }

    private void calcSquare() {
        double p = (this.a+this.b+this.c)/2;
        setSquare(Math.sqrt((p * (p - this.a)*(p - this.b)*(p - this.c))));
    }

    public Triangle(double a, double b, double c) {
        setShapeType(ShapeType.TRIANGLE);
        setA(a);
        setB(b);
        setC(c);
        if ((a<(c+b)) && (b<(a+c)) && (c<(a+b))) {
            calcPerimeter();
            calcSquare();
            printInfo();
        } else {
            System.out.println("Triangle does not exists!");
        }
    }

}
