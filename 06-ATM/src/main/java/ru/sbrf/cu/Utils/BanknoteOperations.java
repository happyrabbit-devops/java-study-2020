package ru.sbrf.cu.Utils;

import ru.sbrf.cu.Enums.BanknoteType;
import ru.sbrf.cu.Impl.BanknoteCell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class BanknoteOperations {

    public static long totalAmount(HashMap<BanknoteType, Integer> banknotes) {
        long amount = 0;
        for (BanknoteType banknoteType : BanknoteType.values()) {
            amount += banknotes.get(banknoteType) * banknoteType.getDenomination();
        }
        return amount;
    }
    public static void withdrawBanknotes(HashMap<BanknoteType, BanknoteCell> banknoteCells, HashMap<BanknoteType, Integer> withdrawBanknotes, String password) {
        Arrays.asList(BanknoteType.values()).
                forEach(banknoteType -> {
                    if (withdrawBanknotes.get(banknoteType) > 0) {
                        banknoteCells.get(banknoteType).Withdraw(withdrawBanknotes.get(banknoteType), password);
                    }
                } );
    }
    public static void depositBanknotes(HashMap<BanknoteType, BanknoteCell> banknoteCells, HashMap<BanknoteType, Integer> putBanknotes, String password) {
        Arrays.asList(BanknoteType.values()).
                forEach(banknoteType -> {
                if (putBanknotes.get(banknoteType) > 0) {
                    banknoteCells.get(banknoteType).Deposit(putBanknotes.get(banknoteType), password);
                }
            } );
    }
}
