package ru.sbrf.cu.Exceptions;

public class CardNotFoundException extends ATMException {

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException() {
        super();
    }
}
