package ru.sbrf.cu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger( Runner.class );

    public static void main(String[] args) {

        logger.info( "Создание 'Класса-работника'" );
        IncPrinter inc = new IncPrinter(1, 10);

        logger.info( "Создание первого потока: 'Thread #1'" );
        Thread firstThread = new Thread(inc);
        firstThread.setName("Thread #1");

        logger.info( "Создание второго потока: 'Thread #2'" );
        Thread secondThread = new Thread(inc);
        secondThread.setName("Thread #2");

        logger.info( "Запуска обоих потоков" );
        firstThread.start();
        secondThread.start();
    }
}
