package ru.sbrf.cu.Exceptions;

public class CardNotFoundException extends Exception {

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException() {
        super();
    }
}
