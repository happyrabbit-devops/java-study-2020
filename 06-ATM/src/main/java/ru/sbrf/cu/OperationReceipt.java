package ru.sbrf.cu;

public interface OperationReceipt {

    /**
     *  Печать квитанции об изъятии денежны средств
     */
    void printInfoWithdraw();

    /**
     *  Печать квитанции о депозите денежных средств
     */
    void printInfoDeposit();
}
