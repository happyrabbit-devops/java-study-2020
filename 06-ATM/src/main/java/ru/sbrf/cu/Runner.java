package ru.sbrf.cu;

import ru.sbrf.cu.impl.ATMImpl;
import ru.sbrf.cu.impl.BanknoteType;
import ru.sbrf.cu.impl.PlasticCardImpl;

import java.util.*;

public class Runner {

    public static void main( String[] args ) {

        // Заполняем БД платежной системы
        var paySystemDB = new HashSet<PlasticCard>();
        paySystemDB.add( new PlasticCardImpl("Maksim Petrov", 1111, 1000) );
        paySystemDB.add( new PlasticCardImpl("Nikolas Klaus", 2222, 50000) );
        paySystemDB.add( new PlasticCardImpl("Pavel Ivanov", 2323, 999999999) );

        // Загружаем банкноты в АТМ
        var banknotes = new HashMap<BanknoteType, Integer>();
        banknotes.put( BanknoteType.ONEHUNDRED, 50 );
        banknotes.put( BanknoteType.FIVEHUNDRED, 25 );
        banknotes.put( BanknoteType.THOUSAND, 15 );
        banknotes.put( BanknoteType.FIVETHOUSAND, 5 );

        ATM atm = new ATMImpl( paySystemDB, banknotes );

        atm.run();
    }
}
