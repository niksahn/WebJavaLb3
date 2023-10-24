/**
 * 22ВП3 в3 Сахно Новосельцев
 */

package com.niksahn.transport;

import static com.niksahn.transport.UI.*;


public class Main {

    public static void main(String[] args) {
        displayInfoMsg();
        initArr();
        intaractionStart();
    }

    /**
     * Базовая инициализация массива
     */
    private static void initArr() {
        for (int i = 1; i < 6; i++) {
            new Auto(
                    "aa111a58",
                    i * 10,
                    i * 1111,
                    2004 + i,
                    "lada",
                    "Праворульная",
                    "Авто",
                    "Седан",
                    "Полный",
                    4
            ).addToArray();
            new Moto(
                    "aa111a58",
                    i * 10,
                    i * 1111,
                    2004 + i,
                    "lada",
                    "Кросс",
                    2,
                    true,
                    false,
                    "бензин"
            ).addToArray();
        }
    }
}
