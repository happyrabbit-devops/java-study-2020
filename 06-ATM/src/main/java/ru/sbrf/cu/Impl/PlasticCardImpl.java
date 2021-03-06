package ru.sbrf.cu.Impl;

import ru.sbrf.cu.Exceptions.ATMException;
import ru.sbrf.cu.Exceptions.NotEnoughATMMoneyException;
import ru.sbrf.cu.Exceptions.NotEnoughMoneyException;
import ru.sbrf.cu.OperationReceipt;
import ru.sbrf.cu.PlasticCard;
import ru.sbrf.cu.Enums.BanknoteType;

import java.util.HashMap;

import static ru.sbrf.cu.Utils.BanknoteOperations.totalAmount;
import static ru.sbrf.cu.Utils.BanknoteOperations.withdrawBanknotes;

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

    public OperationReceipt takeAmount(long amount, HashMap<BanknoteType, BanknoteCell> banknoteCells, String passwordATM) throws ATMException {

        // Далее проверка на остаток на карте
        if ( amount > balance )
            throw new NotEnoughMoneyException();

        // Проверка остатков банкнот в банкомате
        int cnt5000 = banknoteCells.get( BanknoteType.FIVETHOUSAND ).getCount();
        int cnt1000 = banknoteCells.get( BanknoteType.THOUSAND ).getCount();
        int cnt500 = banknoteCells.get( BanknoteType.FIVEHUNDRED ).getCount();
        int cnt100 = banknoteCells.get( BanknoteType.ONEHUNDRED ).getCount();

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
        withdrawBanknotes.put( BanknoteType.ONEHUNDRED, cntWithdraw100 );
        withdrawBanknotes.put( BanknoteType.FIVEHUNDRED, cntWithdraw500 );
        withdrawBanknotes.put( BanknoteType.THOUSAND, cntWithdraw1000 );
        withdrawBanknotes.put( BanknoteType.FIVETHOUSAND, cntWithdraw5000 );

        // Обновляем число банкнот в АТМ
        withdrawBanknotes( banknoteCells, withdrawBanknotes, passwordATM );

        amount = cntWithdraw5000*den5000 + cntWithdraw1000*den1000 + cntWithdraw500*den500 + cntWithdraw100*den100;
        decBalance( amount );

        // Возврат чека об операции
        return new OperationReceiptImpl( amount, withdrawBanknotes );
    }

    public OperationReceipt putBanknotes( HashMap<BanknoteType, Integer> putBanknotes ) {

        long deposit = totalAmount( putBanknotes );

        incBalance ( deposit );

        return new OperationReceiptImpl( deposit );
    }

    public PlasticCardImpl( String lastName, int pinCode, long balance ) {
        this.lastName = lastName;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    PlasticCardImpl( String lastName, int pinCode ) {
        this( lastName, pinCode, 0 );
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
