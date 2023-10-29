package com.niksahn.transport;

import java.util.Arrays;
import java.util.Calendar;

abstract class Transport {
    static int initial_length = 0;
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
     * Добавляет транспорт в массив
     *
     * @throws ArrayIndexOutOfBoundsException выход за границу
     */
    public void addToArray() {
        /*
        if (current == transports.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            transports[current] = this;
            current += 1;
        }
         */
        Transport[] newArray = new Transport[transports.length + 1];
        System.arraycopy(transports, 0, newArray, 0, transports.length);
        newArray[transports.length] = this;
        transports = newArray;
        initial_length += 1;
        current += 1;
    }

    static {
        Transport.transports = new Transport[initial_length];
    }

    /**
     * Задать размер массива
     */
    public static void set_transport_length(int length) {
        Transport.transports = new Transport[length];
    }

    /**
     * Вывод массива
     *
     * @throws EmptyArray если пуст
     */
    public static void display_transport() throws EmptyArray {
        for (int i = 0; i < current; i++) {
            transports[i].display_info();
        }
        if (current == 0) throw new EmptyArray();
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
     *
     * @return цена
     * @throws NullPointerException не найдено
     */
    public static Integer find_cheapest_auto() {
        int cheapest = 99999999;
        Transport cheapest_auto = null;
        for (int i = 0; i < current; i++) {
            if (transports[i].price < cheapest && transports[i] instanceof Auto) {
                cheapest = transports[i].price;
                cheapest_auto = transports[i];
            }
        }
        if (cheapest_auto == null) throw new NullPointerException();
        return cheapest;
    }

    /**
     * Определяет самый маленький пробег для машин старше 3 лет
     * @return значение самого маленького пробега авто
     * @throws NullPointerException в массиве нет авто
     */
    public static Integer find_transport_shortest_mileage() {
        Integer smallest_mileage = Integer.MAX_VALUE;
        Calendar calendar = Calendar.getInstance();
        int Year = calendar.get(Calendar.YEAR);
        // return Arrays.stream(transports).filter((Transport a) -> Year - a.issueYear >= 3 && a instanceof Auto).min((Transport a, Transport b) -> b.mileage - a.mileage).get().mileage;
        for (int i = 0; i < current; i++) {
            if (transports[i].mileage < smallest_mileage && Year - transports[i].issueYear >= 3 && transports[i] instanceof Auto) {
                smallest_mileage = transports[i].mileage;
            }
        }
        if (smallest_mileage==Integer.MAX_VALUE) throw new NullPointerException();
        return smallest_mileage;
    }

    /**
     * Сортировка по году выпуска
     **/
    public static void sort_by_year() {
        Arrays.sort(transports, (Transport a, Transport b) -> b.issueYear - a.issueYear);
    }


    /**
     * Поиск по номеру
     *
     * @return Transport если найдено
     * @throws NullPointerException если не найдено
     **/
    public static Transport find_by_number(String registration_number) {
        for (int i = 0; i < current; i++)
            if (registration_number.equals(transports[i].registration_number)) {
                return transports[i];
            }
        throw new NullPointerException();
    }

    /**
     * Изменение полей базового класса
     *
     * @param change_field номер изменяемого поля [ 1 - registration_number, 2 - mileage, 3 - price, 4 - issueYear, 5 - brand ]
     */
    public void change_fields(int change_field, String new_val) {
        switch (change_field) {
            case 1:
                this.registration_number = new_val;
                break;
            case 2:
                this.mileage = Integer.parseInt(new_val);
                break;
            case 3:
                this.price = Integer.parseInt(new_val);
                break;
            case 4:
                this.issueYear = Integer.parseInt(new_val);
                break;
            case 5:
                this.brand = new_val;
                break;
        }
    }
}

class EmptyArray extends Throwable {}