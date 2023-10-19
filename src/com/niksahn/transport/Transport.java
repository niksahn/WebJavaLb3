package com.niksahn.transport;

import java.util.Arrays;
import java.util.Calendar;

import static com.niksahn.transport.Main.ui;

abstract class Transport {
    String registration_number;
    Integer mileage;
    Integer price;
    Integer issueYear;
    String brand;

    private static Transport[] transports;
    private static int current = 0;

    // Переопределяемый метод для вывода информации
    public abstract void display_info();

    // Размер массива
    static void set_transport_length(int length) {
        Transport.transports = new Transport[length];
    }

    // Вывод массива
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
            ui.displayFullArrayMsg();
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
            ui.displayFullArrayMsg();
        } else {
            transports[current] = this;
            current += 1;
        }
    }

    // Находит самый дешёвый авто
    static Transport cheapest_auto() {
        int cheapest = transports[0].price;
        Transport cheapest_auto = new Auto();
        for (int i = 0; i < current; i++) {
            if (transports[i].price < cheapest && transports[i].getClass() == Auto.class) {
                cheapest = transports[i].price;
                cheapest_auto = transports[i];
            }
        }
        return cheapest_auto;
    }

    // Определяет самый маленький пробег для машин старше 3 лет;
    static int display_transport_shortest_mileage() {

        int smallest_mileage = transports[0].mileage;
        Calendar calendar = Calendar.getInstance();
        int Year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < current; i++) {
            if (transports[i].mileage < smallest_mileage && Year - transports[i].issueYear >= 3) {
                smallest_mileage = transports[i].mileage;
            }
        }
        return smallest_mileage;
    }

    /** Сортировка по году выпуска **/
    static void sort_by_year() {
        Arrays.sort(transports, (Transport a, Transport b) -> b.issueYear - a.issueYear);
    }


    /** Поиск по номеру **/
    static Transport find_by_number(String registration_number) {
        for (int i = 0; i < current; i++)
            if (registration_number.equals(transports[i].registration_number)) {
                return transports[i];
            }
        //ui.displayTransportNotFound();
        return null;
    }

    // Изменение полей базового класса
    void Change_fields(
            String registration_number,
            int issueYear,
            int price,
            String brand,
            int mileage
    ) {
        this.issueYear = issueYear;
        this.registration_number = registration_number;
        this.brand = brand;
        this.mileage = mileage;
        this.price = price;
    }

    // Проверка на заполненноть масисива
    static boolean transport_Full() {
        return current == transports.length;
    }

}