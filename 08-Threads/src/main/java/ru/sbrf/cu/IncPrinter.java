package ru.sbrf.cu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

class IncPrinter implements Runnable {

    private int minNumber;
    private int maxNumber;
    private static final Logger logger = LoggerFactory.getLogger( IncPrinter.class );

    IncPrinter(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    @Override
    synchronized public void run() {

        logger.info( "Начат '" + Thread.currentThread().getName()+ "'" );

        int inc = minNumber;
        boolean asc = true;

        while (inc <= maxNumber && inc > minNumber - 1) {

            if (Thread.currentThread().getName().equals("Thread #1")) {
                System.out.println("Поток '"+Thread.currentThread().getName()+"': "+inc);
            } else {
                System.out.println("Поток '"+Thread.currentThread().getName()+"': "+"  " + inc);
            }
            try {
                notify();
                Thread.sleep( TimeUnit.MILLISECONDS.toMillis( 250 ) );
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (inc == maxNumber) {
                asc = !asc;
            }
            inc = asc ? (inc+1) : (inc-1);

        }

        logger.info( "Завершаем '" + Thread.currentThread().getName()+ "'" );
        Thread.currentThread().interrupt();
        notifyAll();
    }
}
