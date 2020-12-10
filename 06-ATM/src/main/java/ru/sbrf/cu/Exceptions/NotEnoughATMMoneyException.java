package ru.sbrf.cu.Exceptions;

public class NotEnoughATMMoneyException extends ATMException {

    public NotEnoughATMMoneyException(String message) {
        super(message);
    }

    public NotEnoughATMMoneyException() {
        super();
    }
}
