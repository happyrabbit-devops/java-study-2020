package ru.sbrf.cu.impl;

public class CardNotFoundException extends Exception {

    public CardNotFoundException(String message) {
        super(message);
    }

    CardNotFoundException() {
        super();
    }
}
