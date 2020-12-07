package ru.sbrf.cu.Impl;

import lombok.SneakyThrows;
import ru.sbrf.cu.ATM;
import ru.sbrf.cu.Exceptions.ATMException;
import ru.sbrf.cu.Exceptions.CardNotFoundException;
import ru.sbrf.cu.Exceptions.NotEnoughATMMoneyException;
import ru.sbrf.cu.Exceptions.NotEnoughMoneyException;
import ru.sbrf.cu.PlasticCard;
import ru.sbrf.cu.Enums.BanknoteType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static ru.sbrf.cu.Utils.BanknoteOperations.depositBanknotes;

public class ATMImpl implements ATM {

    // делаем сет так как все карты разные
    private Set<PlasticCard> paySystemDB;

    // делаем хэшмап т.к. требуется управление по ключу
    private HashMap<BanknoteType, Integer> banknotes;

    public ATMImpl( Set<PlasticCard> paySystemDB, HashMap<BanknoteType, Integer> banknotes ) {
        this.paySystemDB = paySystemDB;
        this.banknotes = banknotes;
    }

    public PlasticCard withdrawMoney( long amount, PlasticCard cashCardToCheck ) throws ATMException {
        for (PlasticCard plasticCard : paySystemDB) {
            if (plasticCard.equals(cashCardToCheck)) {
                plasticCard.takeAmount( amount, banknotes ).printInfoWithdraw();
                System.out.println( "На вашем счету осталось денежных средств: " + plasticCard.getBalance() + " рублей" );
                return plasticCard;
            }
        }
        throw new CardNotFoundException();
    }

    public PlasticCard depositMoney( HashMap<BanknoteType, Integer> putBanknotes, PlasticCard cashCardToCheck ) throws ATMException {
        for (PlasticCard plasticCard : paySystemDB) {
            if (plasticCard.equals(cashCardToCheck)) {
                plasticCard.putBanknotes(putBanknotes).printInfoDeposit();

                // Обновляем число банкнот в АТМ
                depositBanknotes(banknotes, putBanknotes);

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

                        AtomicReference<Boolean> escapeZero = new AtomicReference<>(false);
                        Arrays.asList(BanknoteType.values()).
                                forEach(banknoteType -> {
                                        System.out.println("Сколько положить купюр '" + banknoteType.getCaption() + "' ? (или 0 для выхода)");
                                        int cnt = inputCardScanner.nextInt();
                                        putBanknotes.put(banknoteType, cnt > 0 ? cnt : 0);
                                        escapeZero.set(cnt > 0);
                                    }
                                );

                        if (!escapeZero.get()) {
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
