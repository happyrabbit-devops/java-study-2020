package ru.sbrf.cu;

import ru.sbrf.cu.Enums.BanknoteType;
import ru.sbrf.cu.Exceptions.ATMException;
import ru.sbrf.cu.Exceptions.CardNotFoundException;
import ru.sbrf.cu.Exceptions.NotEnoughATMMoneyException;
import ru.sbrf.cu.Exceptions.NotEnoughMoneyException;

import java.util.HashMap;

public interface ATM {

    /**
     * Запуск работы банкомата
     */
    void run();

    /**
     * Забрать деньги
     * @param amount - запрашиваемая сумма
     * @param plasticCard - данные авторизации (объект карты)
     */
    PlasticCard withdrawMoney( long amount, PlasticCard plasticCard ) throws ATMException;

    /**
     * Положить деньги
     * @param putBanknotes - набор банкнот для пополнения
     * @param plasticCard - данные авторизации (объект карты)
     */
    PlasticCard depositMoney( HashMap<BanknoteType, Integer> putBanknotes, PlasticCard plasticCard ) throws ATMException;
}
