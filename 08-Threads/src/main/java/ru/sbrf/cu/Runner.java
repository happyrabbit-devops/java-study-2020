package ru.sbrf.cu;

public class Runner {
    public static void main(String[] args) {

        IncPrinter inc = new IncPrinter(1, 10);

        Thread firstThread = new Thread(inc);
        firstThread.setName("Thread #1");
        firstThread.start();

        Thread secondThread = new Thread(inc);
        secondThread.setName("Thread #2");
        secondThread.start();
    }
}
