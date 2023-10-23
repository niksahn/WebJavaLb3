package com.niksahn.transport;


import java.util.Scanner;
import java.util.regex.Pattern;

import static com.niksahn.transport.Constants.*;
import static com.niksahn.transport.Constants.positive_int_pattern;
import static com.niksahn.transport.Transport.*;

abstract class UI {
    static private final Scanner in = new Scanner(System.in);

    static private void displayInfo(String str) {
        System.out.println(str);
    }

    static private String inputInfo() {
        return in.nextLine();
    }

    /**
     * Вывод информационного сообщения
     */
    static public void displayInfoMsg() {
        displayInfo("22ВП3 Бригада 3 Сахно и Новосельцев \n Вариант №3: Класс 'Транспорт' \n");
    }

    /**
     *
     */
    static public void intaractionStart() {
        Boolean flag = false;
        String command = "";
        while (!flag) {
            displayInfo("Что вы хотите сделать?");
            displayHelpList();
            command = inputInfo();
            switch (command) {
                case "1":
                    try {
                        createTransport();
                    } catch (ArrayIndexOutOfBoundsException a) {
                        displayFullArrayMsg();
                    }
                    break;
                case "2":
                    try {
                        find_cheapest_auto().display_info();
                    } catch (NullPointerException e) {
                        displayTransportNotFound();
                    }
                    break;
                case "3":
                    try {
                        displayInfo(find_transport_shortest_mileage().toString());

                    } catch (NullPointerException e) {
                        displayTransportNotFound();
                    }
                    break;
                case "4":
                    sort_by_year();
                    display_transport();
                    break;
                case "5":
                    try {
                        displayInfo("Введите номер транспорта");
                        String number = inputField(number_pattern);
                        changeField(find_by_number(number));
                    } catch (NullPointerException e) {
                        displayTransportNotFound();
                    }
                    break;
                case "6":
                    display_transport();
                    break;
                default:
                    flag = true;
                    break;
            }
        }
    }

    static private void displayHelpList() {
        displayInfo("""
                1 Создать новый транспорт
                2 Найти цену самого дешёвого авто
                3 Определить самый маленький пробег для машин старше 3 лет
                4 Сортировка по году выпуска (и вывод на экран)
                5 Поиск по номеру и изменение
                6 Вывод массива на экран
                Иное выход
                """);
    }

    /**
     * Вывод сообщения о заполненности массива
     */
    static public void displayFullArrayMsg() {
        displayInfo("Массив заполнен");
    }

    static private void displayTransportNotFound() {
        displayInfo("Такого транспорта нет!");
    }

    private static void displayDefault(Transport transport) {
        displayInfo("Цена " + transport.price + "\nПробег " + transport.mileage + "\nМарка "
                + transport.brand + "\nГод выпуска " + transport.issueYear + "\nНомер "
                + transport.registration_number);
    }

    /**
     * Вывод параметров автомобиля
     *
     * @param auto автомобиль
     */
    static public void displayAuto(Auto auto) {
        displayInfo("Автомобиль \n");
        displayDefault(auto);
        displayInfo("Привод " + auto.drive + "\n" + auto.hand + "\nКузов " + auto.body
                + "\nКоробка передач " + auto.transmission + "\nЧисло дверей " + auto.doors + "\n");
    }

    /**
     * Вывод параметров мотоцикла
     * */

    static public void displayMoto(Moto moto) {
        displayInfo("Мотоцикл \n");
        displayDefault(moto);
        displayInfo("Тип " + moto.type + "\n" + "Число колёс " + moto.wheelsNum + "\n"
        + "Топливная система " + moto.type + "\n");
        if(moto.electroStarter) displayInfo("Электростартер");
        if(moto.sidecarEnabled) displayInfo("Возможность установки коляски");

    }

    static public String inputField(Pattern pattern) {
        String a = inputInfo();
        while (!pattern.matcher(a).matches()) {
            displayInfo("Неверное значение, введите другое");
            a = inputInfo();
        }
        return a;
    }

    static private void createTransport() {
        displayInfo("Какой тип транспорта хотите создать 1 - Автомобиль 2 - Мотоцикл");
        String a = inputInfo();
        switch (a) {
            case "1":
                inputAuto().addToArray();
                break;
            case "2":
                break;
        }
    }

    static private void changeField(Transport a) {
        try {
            displayInfo("Введите номер изменяемого поля 1 - registration_number, 2 - mileage, 3 - price," +
                    " 4 - issueYear, 5 - brand hand " +
                    "6 Тип руля 7 Тип трансмиссии 8 Тип кузова 9 Привод 10 Количество дверей");
            String field = inputInfo();

            a.change_fields(Integer.parseInt(field));
        } catch (NumberFormatException ignored) {
        }
    }


// TODO Ввод и вывод мотоцикла
    /**
     * Ввод автомобиля
     */
    static public Auto inputAuto() {
        displayInfo("Введите параметры автомобиля");
        displayInfo("Введите номер");
        String number = inputField(number_pattern);
        displayInfo("Введите пробег");
        int mileage = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите цену");
        int price = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите год");
        int year = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите марку");
        String brand = inputField(string_pattern);
        displayInfo("Введите тип руля (праворульный/леворульный");
        String hand = inputField(hand_pattern);
        displayInfo("Введите тип трансмиссии (авто/механика");
        String transmission = inputField(transmission_pattern);
        displayInfo("Введите тип кузова");
        String body = inputField(string_pattern);
        displayInfo("Введите тип привода (полный/передний/задний)");
        String drive = inputField(drive_pattern);
        displayInfo("Введите число дверей");
        int doors = Integer.parseInt(inputField(positive_int_pattern));
        return new Auto(number, mileage, price, year, brand, hand, transmission, body, drive, doors);
    }

    /**
     * Ввод мотоцикла
     * */
    static public Moto inputMoto() {
        displayInfo("Введите параметры мотоцикла");
        displayInfo("Введите номер");
        String number = inputField(number_pattern);
        displayInfo("Введите пробег");
        int mileage = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите цену");
        int price = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите год");
        int year = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Введите марку");
        String brand = inputField(string_pattern);
        displayInfo("Введите тип мотоцикла");
        String type = inputField(string_pattern);
        displayInfo("Введите число колёс");
        int wheels = Integer.parseInt(inputField(positive_int_pattern));
        displayInfo("Возможно ли установить коляску?");
        Boolean sideCar = Boolean.valueOf(inputField(boolean_pattern));
        displayInfo("Установлен ли электростартер?");
        Boolean electrostarter = Boolean.valueOf(inputField(boolean_pattern));
        displayInfo("Укажите систему питания");
        String fuelSystem = inputField(string_pattern);
        return new Moto(number, mileage, price, year, brand, type, wheels, electrostarter, sideCar, fuelSystem);

    }
}

