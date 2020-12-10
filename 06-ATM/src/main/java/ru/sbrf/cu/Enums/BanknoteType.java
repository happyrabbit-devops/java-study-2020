package ru.sbrf.cu.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип банкноты
 */
@Getter
@AllArgsConstructor
public enum BanknoteType {
    ONEHUNDRED(1, "100 рублей (сто рублей)", 100),
    FIVEHUNDRED(2, "500 рублей (пятьсот рублей)", 500),
    THOUSAND(3, "1000 рублей (тысяча рублей)", 1000),
    FIVETHOUSAND(4, "5000 рублей (пять тысяч рублей)", 5000),
    ;

    /**
     * Порядок сортировки
     */
    private int id;

    /**
     * Название-заголовок
     */
    private String caption;

    /**
     * Номинал
     */
    private int denomination;
}
