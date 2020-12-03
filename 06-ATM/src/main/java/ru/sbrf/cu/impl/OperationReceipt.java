package ru.sbrf.cu.impl;

import java.util.HashMap;

public class OperationReceipt {

    private long withdrawAmount;
    private long depositAmount;
    private HashMap<BanknoteType, Integer> banknotes;

    OperationReceipt(long withdrawAmount, HashMap<BanknoteType, Integer> banknotes) {
        this.withdrawAmount = withdrawAmount;
        this.banknotes = banknotes;
    }

    OperationReceipt(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    void printInfoWithdraw() {
        System.out.println("С вашего счета было снято " + this.withdrawAmount + " рублей купюрами:");
        System.out.println( BanknoteType.FIVETHOUSAND.getCaption() + ": " + banknotes.get(BanknoteType.FIVETHOUSAND) );
        System.out.println( BanknoteType.THOUSAND.getCaption() + ": " + banknotes.get(BanknoteType.THOUSAND)) ;
        System.out.println( BanknoteType.FIVEHUNDRED.getCaption() + ": " + banknotes.get(BanknoteType.FIVEHUNDRED) );
        System.out.println( BanknoteType.ONEHUNDRED.getCaption() + ": " + banknotes.get(BanknoteType.ONEHUNDRED) );
    }

    void printInfoDeposit() {
        System.out.println("На ваш счет было зачислено " + this.depositAmount + " рублей");
    }
}
