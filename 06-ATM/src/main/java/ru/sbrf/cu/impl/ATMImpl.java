package ru.sbrf.cu.impl;

import lombok.SneakyThrows;
import ru.sbrf.cu.ATM;
import ru.sbrf.cu.PlasticCard;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ATMImpl implements ATM {

    // делаем сет так как все карты разные
    private Set<PlasticCard> paySystemDB;

    // делаем хэшмап т.к. требуется управление по ключу
    private HashMap<BanknoteType, Integer> banknotes;

    public ATMImpl( Set<PlasticCard> paySystemDB, HashMap<BanknoteType, Integer> banknotes ) {
        this.paySystemDB = paySystemDB;
        this.banknotes = banknotes;
    }

    public PlasticCard withdrawMoney( long amount, PlasticCard cashCardToCheck ) throws CardNotFoundException, NotEnoughATMMoneyException, NotEnoughMoneyException {
        for (PlasticCard plasticCard : paySystemDB) {
            if (plasticCard.equals(cashCardToCheck)) {
                plasticCard.takeAmount( amount, banknotes ).printInfoWithdraw();
                System.out.println( "На вашем счету осталось денежных средств: " + plasticCard.getBalance() + " рублей" );
                return plasticCard;
            }
        }
        throw new CardNotFoundException();
    }

    public PlasticCard depositMoney( HashMap<BanknoteType, Integer> putBanknotes, PlasticCard cashCardToCheck ) throws CardNotFoundException {
        PlasticCard resultCard = null;
        for (PlasticCard plasticCard : paySystemDB) {
            if (plasticCard.equals(cashCardToCheck)) {
                plasticCard.putBanknotes(putBanknotes).printInfoDeposit();
                return plasticCard;
            }
        }
        throw new CardNotFoundException();
    }

    @SneakyThrows
    public void run() {

        do {
            Scanner inputCardScanner = new Scanner( System.in );
            System.out.println( "Введите фамилию имя на карте" );
            String lastName = inputCardScanner.nextLine();
            System.out.println( "Введите PIN" );
            int pinCode = inputCardScanner.nextInt();
            var cashCardToCheck = new PlasticCardImpl( lastName, pinCode, 0 );

            if ( !paySystemDB.contains( cashCardToCheck ) ) {
                System.out.println( "Неправильно введена фамилия или пинкод!" );
            } else {

                System.out.println( "Введите номер операции: 1 - снять, 2 - положить денежные средства" );
                int operationCode = inputCardScanner.nextInt();

                // Снятие
                if (operationCode == 1) {
                    do {

                        System.out.println( "Введите сумму для снятия или 0 для выхода" );
                        long amount = inputCardScanner.nextLong();
                        if (amount == 0) {
                            break;
                        }
                        try {
                            withdrawMoney(amount, cashCardToCheck);
                        } catch (CardNotFoundException e) {
                            System.out.println( "Карта не найдена в платежной системе" );
                        } catch (NotEnoughMoneyException e) {
                           System.out.println( "На вашем счету недостаточно денежных средств" );
                        } catch (NotEnoughATMMoneyException e) {
                           System.out.println( "В бакомате отсутствуют денежные средства" );
                        }

                    } while (true);

                // Пополнение
                } else if (operationCode == 2) {
                    do {
                        var putBanknotes = new HashMap<BanknoteType, Integer>();

                        System.out.println( "Сколько положить купюр '" + BanknoteType.FIVETHOUSAND.getCaption() + "' ? (или 0 для выхода)" );
                        int cnt1 = inputCardScanner.nextInt();
                        putBanknotes.put(BanknoteType.FIVETHOUSAND, cnt1 > 0 ? cnt1 : 0);

                        System.out.println( "Сколько положить купюр '" + BanknoteType.THOUSAND.getCaption() + "' ? (или 0 для выхода)" );
                        int cnt2 = inputCardScanner.nextInt();
                        putBanknotes.put(BanknoteType.THOUSAND, cnt2 > 0 ? cnt2 : 0);

                        System.out.println( "Сколько положить купюр '" + BanknoteType.FIVEHUNDRED.getCaption() + "' ? (или 0 для выхода)" );
                        int cnt3 = inputCardScanner.nextInt();
                        putBanknotes.put(BanknoteType.FIVEHUNDRED, cnt3 > 0 ? cnt3 : 0);

                        System.out.println( "Сколько положить купюр '" + BanknoteType.ONEHUNDRED.getCaption() + "' ? (или 0 для выхода)" );
                        int cnt4 = inputCardScanner.nextInt();
                        putBanknotes.put(BanknoteType.ONEHUNDRED, cnt4 > 0 ? cnt4 : 0);

                        if (cnt1 == 0 && cnt1 == cnt2 && cnt2 == cnt3 && cnt3 == cnt4) {
                            break;
                        }

                        try {
                            depositMoney(putBanknotes, cashCardToCheck);
                        } catch (CardNotFoundException e) {
                            System.out.println( "Карта не найдена в платежной системе" );
                        }

                    } while (true);
                }

            }
        } while (true);
    }
}
