package ru.sbrf.cu.Exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
        super();
    }
}
