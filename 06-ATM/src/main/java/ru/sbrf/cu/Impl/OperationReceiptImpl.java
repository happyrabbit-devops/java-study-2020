package ru.sbrf.cu.Impl;

import ru.sbrf.cu.OperationReceipt;
import ru.sbrf.cu.Enums.BanknoteType;

import java.util.HashMap;

public class OperationReceiptImpl implements OperationReceipt {

    private long withdrawAmount;
    private long depositAmount;
    private HashMap<BanknoteType, Integer> banknotes;

    OperationReceiptImpl(long withdrawAmount, HashMap<BanknoteType, Integer> banknotes) {
        this.withdrawAmount = withdrawAmount;
        this.banknotes = banknotes;
    }

    OperationReceiptImpl(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void printInfoWithdraw() {
        System.out.println("С вашего счета было снято " + this.withdrawAmount + " рублей купюрами:");
        System.out.println( BanknoteType.FIVETHOUSAND.getCaption() + ": " + banknotes.get(BanknoteType.FIVETHOUSAND) );
        System.out.println( BanknoteType.THOUSAND.getCaption() + ": " + banknotes.get(BanknoteType.THOUSAND)) ;
        System.out.println( BanknoteType.FIVEHUNDRED.getCaption() + ": " + banknotes.get(BanknoteType.FIVEHUNDRED) );
        System.out.println( BanknoteType.ONEHUNDRED.getCaption() + ": " + banknotes.get(BanknoteType.ONEHUNDRED) );
    }

    public void printInfoDeposit() {
        System.out.println("На ваш счет было зачислено " + this.depositAmount + " рублей");
    }
}
