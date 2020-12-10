package ru.sbrf.cu;

import ru.sbrf.cu.Enums.BanknoteType;
import ru.sbrf.cu.Exceptions.ATMException;
import ru.sbrf.cu.Exceptions.NotEnoughATMMoneyException;
import ru.sbrf.cu.Exceptions.NotEnoughMoneyException;
import ru.sbrf.cu.Impl.BanknoteCell;

import java.util.HashMap;

public interface PlasticCard {

    /**
     * Выдача наличных
     * @param amount - запрашиваемая сумма
     * @param banknoteCells - ячейки по банкнотам на АТМ
     * @return - OperationReceipt - отчет
     */
    OperationReceipt takeAmount(long amount, HashMap<BanknoteType, BanknoteCell> banknoteCells, String passwordATM ) throws ATMException;

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
