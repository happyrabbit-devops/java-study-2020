package ru.sbrf.cu.Exceptions;

public class NotEnoughMoneyException extends ATMException {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
        super();
    }
}
