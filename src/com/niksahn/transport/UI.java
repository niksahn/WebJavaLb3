package com.niksahn.transport;


import java.util.Scanner;
import java.util.regex.Pattern;

import static com.niksahn.transport.Constants.*;
import static com.niksahn.transport.Constants.positive_int_pattern;
import static com.niksahn.transport.Transport.*;
import static com.niksahn.transport.Transport.display_transport;

class UI {
    static private final Scanner in = new Scanner(System.in);

    public static void displayInfo(String str) {
        System.out.println(str);
    }
    public void display(String str) {
        System.out.println(str);
    }

    static String inputInfo() {
        return in.nextLine();
    }

    /**
     * Вывод информационного сообщения
     */
    static public void displayInfoMsg() {
        displayInfo("22ВП3 Бригада 3 Сахно и Новосельцев \n Вариант №3: Класс 'Транспорт' \n");
    }

     public void intaractionStart() {
        boolean flag = false;
        String command;
        while (!flag) {
            displayInfo("\nЧто вы хотите сделать?");
            displayHelpList();
            command = inputInfo();
            try {
                switch (command) {
                    case "1":
                        createTransport().addToArray();
                        break;
                    case "2":
                        displayInfo(find_cheapest_auto().toString());
                        break;
                    case "3":
                        displayInfo(find_transport_shortest_mileage().toString());
                        break;
                    case "4":
                        sort_by_year();
                        display_transport(this);
                        break;
                    case "5":
                        displayInfo("Введите номер транспорта");
                        String number = inputField(number_pattern);
                        changeField(find_by_number(number)).display_info(this);
                        break;
                    case "6":
                        display_transport(this);
                        break;
                    default:
                        flag = true;
                        break;
                }
            } catch (NullPointerException e) {
                displayTransportNotFound();
            } catch (RepeatNumberExeption a) {
                displayNumberIsNotUnique();
            }
        }
    }

    static void displayHelpList() {
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
     * Вывод сообщения о том, что транспорт не найден
     */
    static void displayTransportNotFound() {
        displayInfo("Такого транспорта нет!");
    }

    /**
     * Вывод сообщения о том, что транспорт не найден
     */
    static void displayNumberIsNotUnique() {
        displayInfo("Транспорт с данным номером уже существует!");
    }

    /**
     * Ввод поля по паттерну
     *
     * @param pattern паттерн
     */
     public String inputField(Pattern pattern) {
        String a = inputInfo();
        while (!pattern.matcher(a).matches()) {
            displayInfo("Неверное значение, введите другое");
            a = inputInfo();
        }
        return a;
    }

    /**
     * Создать транспорт
     */
     Transport createTransport() {
        displayInfo("Какой тип транспорта хотите создать 1 - Автомобиль 2 - Мотоцикл");
        String a = inputInfo();
        return switch (a) {
            case "1" -> inputAuto();
            case "2" -> inputMoto();
            default -> null;
        };
    }

    /**
     * Изменение поля объекта [Transport]
     *
     * @param a изменяемый транспорт
     * @return измененный транспорт
     */
     Transport changeField(Transport a) {
        try {
            if (a instanceof Auto)
                displayInfo("Введите номер изменяемого поля 1 - registration_number, 2 - mileage, 3 - price," +
                        " 4 - issueYear, 5 - brand hand " +
                        "6 Тип руля 7 Тип трансмиссии 8 Тип кузова 9 Привод 10 Количество дверей");
            else if (a instanceof Moto)
                displayInfo("Введите номер изменяемого поля 1 - registration_number, 2 - mileage, 3 - price," +
                        " 4 - issueYear, 5 - brand hand " +
                        "6 Тип мотоцикла 7 Количество колёс 8 Возможность установки коляски 9 Наличие электростартера 10 Тип топливной системы");
            int field = Integer.parseInt(inputInfo());
            switch (field) {
                case 1:
                    a.change_fields(field, inputField(number_pattern));
                    break;
                case 2, 3, 4:
                    a.change_fields(field, inputField(positive_int_pattern));
                    break;
                case 5:
                    a.change_fields(field, inputField(string_pattern));
                    break;
                default:
                    if (a instanceof Auto) {
                        switch (field) {
                            case 6:
                                a.change_fields(field, inputField(hand_pattern));
                                break;
                            case 7:
                                a.change_fields(field, inputField(positive_int_pattern));
                                break;
                            case 8:
                                a.change_fields(field, inputField(string_pattern));
                                break;
                            case 9:
                                a.change_fields(field, inputField(transmission_pattern));
                                break;
                            case 10:
                                a.change_fields(field, inputField(drive_pattern));
                                break;
                        }
                    } else if (a instanceof Moto) {
                        switch (field) {
                            case 6, 8, 10:
                                a.change_fields(field, inputField(string_pattern));
                                break;
                            case 7, 9:
                                a.change_fields(field, inputField(boolean_pattern));
                                break;
                        }
                    }
            }
        } catch (NumberFormatException ignored) {
        }
        return a;
    }


    /**
     * Ввод автомобиля
     */
     public Auto inputAuto() {
        displayInfo("Введите параметры автомобиля");
        displayInfo("Введите номер");
        String number = inputField(number_pattern);
        checkNumberUnique(number);
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
     */
     public Moto inputMoto() {
        displayInfo("Введите параметры мотоцикла");
        displayInfo("Введите номер");
        String number = inputField(number_pattern);
        checkNumberUnique(number);
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
        displayInfo("Возможно ли установить коляску?(0 - если нет, 1 - если да)");
        Boolean sideCar = Boolean.valueOf(inputField(boolean_pattern));
        displayInfo("Установлен ли электростартер?(0 - если нет, 1 - если да)");
        Boolean electrostarter = Boolean.valueOf(inputField(boolean_pattern));
        displayInfo("Укажите систему питания");
        String fuelSystem = inputField(string_pattern);
        return new Moto(number, mileage, price, year, brand, type, wheels, electrostarter, sideCar, fuelSystem);
    }
}

