package ru.sbrf.cu.impl;

import java.util.HashMap;

class BanknoteOperations {
    static void depositBanknotes(HashMap<BanknoteType, Integer> atmBanknotes, HashMap<BanknoteType, Integer> putBanknotes) {
        atmBanknotes.put( BanknoteType.ONEHUNDRED, atmBanknotes.get(BanknoteType.ONEHUNDRED) + putBanknotes.get(BanknoteType.ONEHUNDRED) );
        atmBanknotes.put( BanknoteType.FIVEHUNDRED, atmBanknotes.get(BanknoteType.FIVEHUNDRED) + putBanknotes.get(BanknoteType.FIVEHUNDRED) );
        atmBanknotes.put( BanknoteType.THOUSAND, atmBanknotes.get(BanknoteType.THOUSAND) + putBanknotes.get(BanknoteType.THOUSAND) );
        atmBanknotes.put( BanknoteType.FIVETHOUSAND, atmBanknotes.get(BanknoteType.FIVETHOUSAND) + putBanknotes.get(BanknoteType.FIVETHOUSAND) );
    }
    static void withdrawBanknotes(HashMap<BanknoteType, Integer> atmBanknotes, HashMap<BanknoteType, Integer> withdrawBanknotes) {
        atmBanknotes.put( BanknoteType.ONEHUNDRED, atmBanknotes.get(BanknoteType.ONEHUNDRED) - withdrawBanknotes.get(BanknoteType.ONEHUNDRED) );
        atmBanknotes.put( BanknoteType.FIVEHUNDRED, atmBanknotes.get(BanknoteType.FIVEHUNDRED) - withdrawBanknotes.get(BanknoteType.FIVEHUNDRED) );
        atmBanknotes.put( BanknoteType.THOUSAND, atmBanknotes.get(BanknoteType.THOUSAND) - withdrawBanknotes.get(BanknoteType.THOUSAND) );
        atmBanknotes.put( BanknoteType.FIVETHOUSAND, atmBanknotes.get(BanknoteType.FIVETHOUSAND) - withdrawBanknotes.get(BanknoteType.FIVETHOUSAND) );
    }
    static long totalAmount(HashMap<BanknoteType, Integer> banknotes) {
        return banknotes.get( BanknoteType.ONEHUNDRED ) * BanknoteType.ONEHUNDRED.getDenomination() +
                banknotes.get( BanknoteType.FIVEHUNDRED ) * BanknoteType.FIVEHUNDRED.getDenomination() +
                banknotes.get( BanknoteType.THOUSAND ) * BanknoteType.THOUSAND.getDenomination() +
                banknotes.get( BanknoteType.FIVETHOUSAND ) * BanknoteType.FIVETHOUSAND.getDenomination();
    }
}
