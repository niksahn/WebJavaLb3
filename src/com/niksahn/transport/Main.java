/**
 * 22ВП3 в3 Сахно Новосельцев
 */

package com.niksahn.transport;

import static com.niksahn.transport.UI.*;


public class Main {

    static final UI  ui = new UI();
    public static void main(String[] args) {
        displayInfoMsg();
        initArr();
        ui.intaractionStart();
    }

    /**
     * Базовая инициализация массива
     */
    private static void initArr() {
        for (int i = 1; i < 6; i++) {
            new Auto(
                    "a11" + i + "aa58",
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
                    "a" + i + "11am58",
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
