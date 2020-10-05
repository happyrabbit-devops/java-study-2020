package ru.sbrf.cu;

import java.io.*;

public class App
{
    public static void main( String[] args ) throws IOException {

        Shape shape = null;

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String shapeName;

        do {

            System.out.print("Please type shape name (triangle, rectangle, square) or type 'exit': ");
            shapeName = bufferedReader.readLine();

            double a;
            double b;
            double c;

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

                    System.out.print("A = ");
                    a = Double.parseDouble(bufferedReader.readLine());
                    System.out.print("B = ");
                    b = Double.parseDouble(bufferedReader.readLine());

                    shape = new Rectangle(a, b);

                    break;

                case "square":

                    System.out.print("A = ");
                    a = Double.parseDouble(bufferedReader.readLine());

                    shape = new Square(a);

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
