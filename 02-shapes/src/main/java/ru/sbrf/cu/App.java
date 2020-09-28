package ru.sbrf.cu;

import java.io.*;

public class App
{
    public static void main( String[] args ) throws IOException {

        Shape shape = null;

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String shapeName = "";

        do {

            System.out.print("Please type shape name (triangle, rectangle, square) or type 'exit': ");
            shapeName = bufferedReader.readLine();

            Double a;
            Double b;
            Double c;

            switch (shapeName) {

                case "triangle":

                    System.out.print("A = ");
                    a = Double.parseDouble(bufferedReader.readLine());
                    System.out.print("B = ");
                    b = Double.parseDouble(bufferedReader.readLine());
                    System.out.print("C = ");
                    c = Double.parseDouble(bufferedReader.readLine());

                    shape = new Triangle(a, b, c);

                    break;

                case "rectangle":
                case "square":

                    System.out.print("A = ");
                    a = Double.parseDouble(bufferedReader.readLine());
                    System.out.print("B = ");
                    b = Double.parseDouble(bufferedReader.readLine());

                    if (shapeName.equals("rectangle")) {
                        shape = new Rectangle(a, b);
                    } else {
                        shape = new Square(a, b);
                    }

                    break;

                case "exit":
                    break;

                default:
                    System.out.println("Shape type not found!");
                    break;
            }

            if (!shapeName.equals("exit")) {
                System.out.println();
                System.out.println(shape);
                System.out.println();
            }

        } while (!shapeName.equals("exit"));
    }
}
