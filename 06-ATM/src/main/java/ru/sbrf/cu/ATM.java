package ru.sbrf.cu;

import ru.sbrf.cu.impl.BanknoteType;
import ru.sbrf.cu.impl.CardNotFoundException;
import ru.sbrf.cu.impl.NotEnoughATMMoneyException;
import ru.sbrf.cu.impl.NotEnoughMoneyException;

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
    PlasticCard withdrawMoney( long amount, PlasticCard plasticCard) throws CardNotFoundException, NotEnoughATMMoneyException, NotEnoughMoneyException;

    /**
     * Положить деньги
     * @param putBanknotes - набор банкнот для пополнения
     * @param plasticCard - данные авторизации (объект карты)
     */
    PlasticCard depositMoney( HashMap<BanknoteType, Integer> putBanknotes, PlasticCard plasticCard ) throws CardNotFoundException;
}
