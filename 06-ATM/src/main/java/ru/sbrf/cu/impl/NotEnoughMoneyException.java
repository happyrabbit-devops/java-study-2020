package ru.sbrf.cu.impl;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    NotEnoughMoneyException() {
        super();
    }
}
