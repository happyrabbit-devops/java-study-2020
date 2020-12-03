package ru.sbrf.cu.Exceptions;

public class NotEnoughATMMoneyException extends Exception {

    public NotEnoughATMMoneyException(String message) {
        super(message);
    }

    public NotEnoughATMMoneyException() {
        super();
    }
}
