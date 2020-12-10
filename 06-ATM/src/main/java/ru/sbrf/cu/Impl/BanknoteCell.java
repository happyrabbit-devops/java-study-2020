package ru.sbrf.cu.Impl;

import ru.sbrf.cu.Enums.BanknoteType;

public class BanknoteCell {
    private final String secretPassword = "uZVXMJ" ;
    private BanknoteType banknoteType;

    int getCount() {
        return count;
    }

    private int count;

    BanknoteCell(BanknoteType banknoteType, int count) {
        this.banknoteType = banknoteType;
        this.count = count;
    }

    public int Withdraw(int inc, String password) {
        if (secretPassword.equals(password)) {
            this.count -= inc;
        }
        return this.count;
    }

    public int Deposit(int inc, String password) {
        if (secretPassword.equals(password)) {
            this.count += inc;
        }
        return this.count;
    }
}
