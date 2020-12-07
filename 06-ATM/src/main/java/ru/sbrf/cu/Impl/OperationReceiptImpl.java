package ru.sbrf.cu.Impl;

import ru.sbrf.cu.OperationReceipt;
import ru.sbrf.cu.Enums.BanknoteType;

import java.util.Arrays;
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

        Arrays.asList(BanknoteType.values()).
                forEach(banknoteType -> System.out.println( banknoteType.getCaption() + ": " + banknotes.get(banknoteType) ));
    }

    public void printInfoDeposit() {
        System.out.println("На ваш счет было зачислено " + this.depositAmount + " рублей");
    }
}
