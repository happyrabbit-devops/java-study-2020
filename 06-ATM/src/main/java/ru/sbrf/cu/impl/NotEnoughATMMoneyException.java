package ru.sbrf.cu.impl;

public class NotEnoughATMMoneyException extends Exception {

    public NotEnoughATMMoneyException(String message) {
        super(message);
    }

    NotEnoughATMMoneyException() {
        super();
    }
}
