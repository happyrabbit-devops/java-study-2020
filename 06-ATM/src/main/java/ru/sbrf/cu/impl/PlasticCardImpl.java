package ru.sbrf.cu.impl;

import ru.sbrf.cu.PlasticCard;

import java.util.HashMap;

public class PlasticCardImpl implements PlasticCard {

    private String lastName;
    private int pinCode;
    private long balance;
    private void decBalance(long balance) {
        this.balance -= balance;
    }
    private void incBalance(long balance) { this.balance += balance; }

    public long getBalance() {
        return balance;
    }

    public OperationReceipt takeAmount(long amount, HashMap<BanknoteType, Integer> atmBanknotes) throws NotEnoughMoneyException, NotEnoughATMMoneyException {

        // Далее проверка на остаток на карте
        if ( amount > balance )
            throw new NotEnoughMoneyException();

        // Проверка остатков банкнот в банкомате
        int cnt5000 = atmBanknotes.get( BanknoteType.FIVETHOUSAND );
        int cnt1000 = atmBanknotes.get( BanknoteType.THOUSAND );
        int cnt500 = atmBanknotes.get( BanknoteType.FIVEHUNDRED );
        int cnt100 = atmBanknotes.get( BanknoteType.ONEHUNDRED );

        if ( cnt5000 == 0 && cnt5000 == cnt1000 && cnt1000 == cnt500 && cnt500 == cnt100 )
            throw new NotEnoughATMMoneyException();

        // Номиналы
        int den5000 = BanknoteType.FIVETHOUSAND.getDenomination();
        int den1000 = BanknoteType.THOUSAND.getDenomination();
        int den500 = BanknoteType.FIVEHUNDRED.getDenomination();
        int den100 = BanknoteType.ONEHUNDRED.getDenomination();

        // Если в банкомате 5000 банкнот меньше, берем все что есть
        int cntWithdraw5000 = (int) amount / den5000;
        cntWithdraw5000 = cntWithdraw5000 >= cnt5000 ? cnt5000 : cntWithdraw5000;

        // Если в банкомате 1000 банкнот меньше, берем все что есть
        int cntWithdraw1000 = (int) ( amount - cntWithdraw5000 * den5000 ) / den1000;
        cntWithdraw1000 = cntWithdraw1000 >= cnt1000 ? cnt1000 : cntWithdraw1000;

        // Если в банкомате 500 банкнот меньше, берем все что есть
        int cntWithdraw500 = (int) ( amount - cntWithdraw5000 * den5000 - cntWithdraw1000 * den1000 ) / den500;
        cntWithdraw500 = cntWithdraw500 >= cnt500 ? cnt500 : cntWithdraw500;

        // Если в банкомате 100 банкнот меньше, берем все что есть
        int cntWithdraw100 = (int) ( amount - cntWithdraw5000 * den5000 - cntWithdraw1000 * den1000 - cntWithdraw500 * den500 ) / den100;
        cntWithdraw100 = cntWithdraw100 >= cnt100 ? cnt100 : cntWithdraw100;

        // Выдаем банкноты
        var withdrawBanknotes = new HashMap<BanknoteType, Integer>();
        withdrawBanknotes.put(BanknoteType.ONEHUNDRED, cntWithdraw100);
        withdrawBanknotes.put(BanknoteType.FIVEHUNDRED, cntWithdraw500);
        withdrawBanknotes.put(BanknoteType.THOUSAND, cntWithdraw1000);
        withdrawBanknotes.put(BanknoteType.FIVETHOUSAND, cntWithdraw5000);

        // Обновляем число банкнот в АТМ
        atmBanknotes.put(BanknoteType.ONEHUNDRED, atmBanknotes.get(BanknoteType.ONEHUNDRED) - cntWithdraw100);
        atmBanknotes.put(BanknoteType.FIVEHUNDRED, atmBanknotes.get(BanknoteType.FIVEHUNDRED) - cntWithdraw500);
        atmBanknotes.put(BanknoteType.THOUSAND, atmBanknotes.get(BanknoteType.THOUSAND) - cntWithdraw1000);
        atmBanknotes.put(BanknoteType.FIVETHOUSAND, atmBanknotes.get(BanknoteType.FIVETHOUSAND) - cntWithdraw5000);

        amount = cntWithdraw5000*den5000 + cntWithdraw1000*den1000 + cntWithdraw500*den500 + cntWithdraw100*den100;
        decBalance( amount );

        // Возврат чека об операции
        return new OperationReceipt( amount, withdrawBanknotes );
    }

    public OperationReceipt putBanknotes( HashMap<BanknoteType, Integer> putBanknotes ) {

        long deposit = putBanknotes.get(BanknoteType.ONEHUNDRED) * BanknoteType.ONEHUNDRED.getDenomination() +
                       putBanknotes.get(BanknoteType.FIVEHUNDRED) * BanknoteType.FIVEHUNDRED.getDenomination() +
                       putBanknotes.get(BanknoteType.THOUSAND) * BanknoteType.THOUSAND.getDenomination() +
                       putBanknotes.get(BanknoteType.FIVETHOUSAND) * BanknoteType.FIVETHOUSAND.getDenomination();

        incBalance ( deposit );

        return new OperationReceipt( deposit );
    }

    public PlasticCardImpl( String lastName, int pinCode, long balance ) {
        this.lastName = lastName;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    PlasticCardImpl(String lastName, int pinCode) {
        this(lastName, pinCode, 0);
    }

    @Override
    public boolean equals( Object o ) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        if (o == this)
            return true;
        PlasticCardImpl that = (PlasticCardImpl) o;
        return this.lastName.equals( that.lastName ) && this.pinCode == that.pinCode;
    }

    @Override
    public int hashCode() {
        int result = 16;
        int prime = 32;

        result = result * prime + lastName.hashCode();
        result = result * prime + pinCode;

        return result;
    }
}
