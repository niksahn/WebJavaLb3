package com.niksahn.transport;

import java.util.Arrays;
import java.util.Calendar;

import static com.niksahn.transport.Constants.*;
import static com.niksahn.transport.UI.displayFullArrayMsg;
import static com.niksahn.transport.UI.inputField;


abstract class Transport {
    protected String registration_number;
    protected Integer mileage;
    protected Integer price;
    protected Integer issueYear;
    protected String brand;

    private static Transport[] transports;
    private static int current = 0;

    /**
     * Переопределяемый метод для вывода информации
     */
    public abstract void display_info();

    public void addToArray(){
        if (current == transports.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            transports[current] = this;
            current += 1;
        }
    }

    /**
     * Задать размер массива
     */
    static void set_transport_length(int length) {
        Transport.transports = new Transport[length];
    }

    /** Вывод массива */
    static void display_transport() {
        for (int i = 0; i < current; i++) {
            transports[i].display_info();
        }
    }

    // Конструктор по умолчанию
    Transport() {
        this.registration_number = null;
        this.brand = null;
        this.price = 0;
        this.mileage = 0;
        this.issueYear = null;
    }

    // Конструктор
    Transport(
            String registration_number,
            int mileage,
            int price,
            int issueYear,
            String brand
    ) {
        this.registration_number = registration_number;
        this.mileage = mileage;
        this.price = price;
        this.issueYear = issueYear;
        this.brand = brand;
    }

    /**
     * Находит цену самого дешёвого авто
     * @return цена или null - если автомобилей нет
     */
    static Transport find_cheapest_auto() {
        int cheapest = 99999999;
        Transport cheapest_auto = null;
        for (int i = 0; i < current; i++) {
            if (transports[i].price < cheapest && transports[i] instanceof Auto) {
                cheapest = transports[i].price;
                cheapest_auto = transports[i];
            }
        }
        return cheapest_auto;
    }

    /**
     * Определяет самый маленький пробег для машин старше 3 лет
     */
    static Integer find_transport_shortest_mileage() {
        Integer smallest_mileage = transports[0].mileage;
        Calendar calendar = Calendar.getInstance();
        int Year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < current; i++) {
            if (transports[i].mileage < smallest_mileage && Year - transports[i].issueYear >= 3 && transports[i] instanceof Auto) {
                smallest_mileage = transports[i].mileage;
            }
        }
        return smallest_mileage;
    }

    /**
     * Сортировка по году выпуска
     **/
    static void sort_by_year() {
        Arrays.sort(transports, (Transport a, Transport b) -> b.issueYear - a.issueYear);
    }


    /**
     * Поиск по номеру
     *
     * @return null если не найдено, Transport если найдено
     **/
    static Transport find_by_number(String registration_number) {
        for (int i = 0; i < current; i++)
            if (registration_number.equals(transports[i].registration_number)) {
                return transports[i];
            }
        return null;
    }

    /**
     * Изменение полей базового класса
     *
     * @param change_field    номер изменяемого поля [ 1 - registration_number, 2 - mileage, 3 - price, 4 - issueYear, 5 - brand ]
     */
    public void change_fields(int change_field) {
        switch (change_field) {
            case 1:
                this.registration_number = inputField(number_pattern);
                break;
            case 2:
                this.mileage = Integer.parseInt(inputField(positive_int_pattern));
                break;
            case 3:
                this.price = Integer.parseInt(inputField(positive_int_pattern));
                break;
            case 4:
                this.issueYear = Integer.parseInt(inputField(positive_int_pattern));
                break;
            case 5:
                this.brand = inputField(string_pattern);
                break;
        }
    }


    /**
     * Проверка на заполненноть масисива
     */
    static boolean transport_Full() {
        return current == transports.length;
    }

}