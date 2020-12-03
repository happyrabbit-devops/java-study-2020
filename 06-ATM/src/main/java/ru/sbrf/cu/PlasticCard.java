package ru.sbrf.cu;

import ru.sbrf.cu.impl.BanknoteType;
import ru.sbrf.cu.impl.NotEnoughATMMoneyException;
import ru.sbrf.cu.impl.NotEnoughMoneyException;

import java.util.HashMap;

public interface PlasticCard {

    /**
     * Выдача наличных
     * @param amount - запрашиваемая сумма
     * @param banknotes - остаток по банкнотам на АТМ
     * @return - OperationReceipt - отчет
     * @throws NotEnoughMoneyException, NotEnoughATMMoneyException
     */
    OperationReceipt takeAmount( long amount, HashMap<BanknoteType, Integer> banknotes ) throws NotEnoughMoneyException, NotEnoughATMMoneyException;

    /**
     * Депозит наличных
     * @param putBanknotes - набор банкнот для пополнения
     * @return OperationReceipt - отчет
     */
    OperationReceipt putBanknotes( HashMap<BanknoteType, Integer> putBanknotes );

    /**
     * Запрос баланса
     * @return сумма баланса на карте
     */
    long getBalance();
}
