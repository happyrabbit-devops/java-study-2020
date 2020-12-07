package ru.sbrf.cu.Exceptions;

public class ATMException extends RuntimeException {

    ATMException(String message) {
        super(message);
    }

    ATMException() {
        super();
    }
}
