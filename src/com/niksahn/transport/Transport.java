package com.niksahn.transport;

import java.util.Arrays;
import java.util.Calendar;

import static com.niksahn.transport.UI.displayFullArrayMsg;


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

        if (current == transports.length) {
            displayFullArrayMsg();
        } else {
            transports[current] = this;
            current += 1;
        }
    }

    // Конструктор
    Transport(
            String registration_number,
            int mileage,
            int price,
            int issueYear,
            String type
    ) {
        this.registration_number = registration_number;
        this.mileage = mileage;
        this.price = price;
        this.issueYear = issueYear;
        this.brand = type;

        if (current == transports.length) {
            displayFullArrayMsg();
        } else {
            transports[current] = this;
            current += 1;
        }
    }

    /**
     * Находит цену самого дешёвый авто
     * @return цена
     */
    static Transport cheapest_auto() {
        int cheapest = transports[0].price;
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
    static int display_transport_shortest_mileage() {
        int smallest_mileage = transports[0].mileage;
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
     * @param new_field_value новое значение
     */
    public void change_fields(int change_field, String new_field_value) {
        switch (change_field) {
            case 1:
                this.registration_number = new_field_value;
                break;
            case 2:
                this.mileage = Integer.parseInt(new_field_value);
                break;
            case 3:
                this.price = Integer.parseInt(new_field_value);
                break;
            case 4:
                this.issueYear = Integer.parseInt(new_field_value);
                break;
            case 5:
                this.brand = new_field_value;
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