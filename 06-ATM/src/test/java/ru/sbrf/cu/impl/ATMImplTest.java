package ru.sbrf.cu.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sbrf.cu.ATM;
import ru.sbrf.cu.PlasticCard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ATMImplTest {

    private ATM atm;

    @BeforeEach
    void init() {

        // Заполняем БД платежной системы
        var paySystemDB = new HashSet<PlasticCard>();
        paySystemDB.add( new PlasticCardImpl("Maksim Petrov", 1111, 1000) );
        paySystemDB.add( new PlasticCardImpl("Nikolas Klaus", 2222, 50000) );
        paySystemDB.add( new PlasticCardImpl("Pavel Ivanov", 2323, 9999999) );

        // Загружаем банкноты в АТМ
        var banknotes = new HashMap<BanknoteType, Integer>();
        banknotes.put( BanknoteType.ONEHUNDRED, 50 );
        banknotes.put( BanknoteType.FIVEHUNDRED, 25 );
        banknotes.put( BanknoteType.THOUSAND, 15 );
        banknotes.put( BanknoteType.FIVETHOUSAND, 5 );

        atm = new ATMImpl(paySystemDB, banknotes);
    }

    @Test
    @DisplayName("Проверка снятия последней тысячи рублей")
    void withdrawMoney() throws CardNotFoundException, NotEnoughATMMoneyException, NotEnoughMoneyException {
        var plasticCard = atm.withdrawMoney(1000, new PlasticCardImpl( "Maksim Petrov", 1111));
        assertEquals(0, plasticCard.getBalance());
    }

    @Test
    @DisplayName("Проверка снятия некратной купюрам суммы")
    void withdrawMoney2() throws CardNotFoundException, NotEnoughATMMoneyException, NotEnoughMoneyException {
        var plasticCard = atm.withdrawMoney(12345, new PlasticCardImpl( "Nikolas Klaus", 2222));
        assertEquals(37700, plasticCard.getBalance());
    }

    @Test
    @DisplayName("Положить деньги")
    void depositMoney() throws CardNotFoundException {

        // Загружаем банкноты в АТМ на свой счет по 1 купюре = 6600
        var myBanknotes = new HashMap<BanknoteType, Integer>();
        myBanknotes.put( BanknoteType.ONEHUNDRED, 1 );
        myBanknotes.put( BanknoteType.FIVEHUNDRED, 1 );
        myBanknotes.put( BanknoteType.THOUSAND, 1 );
        myBanknotes.put( BanknoteType.FIVETHOUSAND, 1 );

        var plasticCard = atm.depositMoney(myBanknotes, new PlasticCardImpl( "Pavel Ivanov", 2323));
        assertEquals(10006599, plasticCard.getBalance());
    }

    @DisplayName("Ошибка если карта не найдена")
    @Test
    void testCardNotFound() {
        assertThrows( CardNotFoundException.class, () ->
                System.out.println(atm.withdrawMoney(12345, new PlasticCardImpl("Santa Klaus", 7777)))
        );
    }

    @DisplayName("Ошибка если на балансе нет денег")
    @Test
    void testNotEnoughMoney() {
        assertThrows( NotEnoughMoneyException.class, () ->
                System.out.println(atm.withdrawMoney(999999999, new PlasticCardImpl( "Pavel Ivanov", 2323)))
        );
    }

    @DisplayName("Ошибка если в банкомате нет денег")
    @Test
    void testATMNotEnoughMoney() throws NotEnoughATMMoneyException, NotEnoughMoneyException, CardNotFoundException {

        // Кто-то снял все деньги
        var pavelPlasticCard = atm.withdrawMoney(500000, new PlasticCardImpl( "Pavel Ivanov", 2323));

        // Теперь другой пытается снять что-нибудь
        assertThrows( NotEnoughATMMoneyException.class, () ->
                System.out.println(atm.withdrawMoney(50000, new PlasticCardImpl( "Nikolas Klaus", 2222)))
        );
    }
}
