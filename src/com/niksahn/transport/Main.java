package com.niksahn.transport;

import java.util.Scanner;
import java.util.regex.Pattern;

import static com.niksahn.transport.Constants.number_pattern;
import static com.niksahn.transport.UI.displayInfoMsg;

public class Main {

    public static void main(String[] args) {
        displayInfoMsg();
        Scanner in = new Scanner(System.in);
        Transport.set_transport_length(6);
        System.out.println("Вы хотите создать реку? (да или  нет)");
        String createRiverCheck = in.nextLine();
        while (!"нет".equals(createRiverCheck) &&
                !Transport.transport_Full()) {
            System.out.println("Какую реку вы хотите создать? (1 - Горная, 2 – Равнинная");
            if (Integer.parseInt(in.nextLine()) == 1) {
                // River newRiver = new Mountain_River(in);
            } else if (Integer.parseInt(in.nextLine()) == 2) {
                //River newRiver = new Flat_River(in);
            }
            System.out.println("Продолжить?");
            createRiverCheck = in.nextLine();
        }
        Transport.display_transport();

    }
}

class Constants {
    private static final String number_regex = "\s*[A-Fa-f]([0-9]){3}[A-Fa-f]{2}([0-9]){2}\s*";
    public static Pattern number_pattern = Pattern.compile(number_regex);

}

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
     * Вывод сообщения о заполненности массива
     */
    static public void displayFullArrayMsg() {
        displayInfo("Массив заполнен");
    }
    //  /**  */    static  public  void  displayTransportNotFound(){       displayInfo("Такого транспорта нет!");    }

    private static void displayDefault(Transport transport) {
        displayInfo("Цена " + transport.price + "\n Пробег " + transport.mileage + "\n Марка "
                + transport.brand + "\n Год выпуска " + transport.issueYear + "\n Номер "
                + transport.registration_number + "\n");
    }

    /**
     * Вывод параметров автомобиля
     *
     * @param auto автомобиль
     */
    static public void displayAuto(Auto auto) {
        displayInfo("Автомобиль \n");
        displayDefault(auto);
        displayInfo("Привод " + auto.drive + "\n" + auto.hand + "\n Кузов " + auto.body
                + "\n Коробка передач " + auto.transmission + "\n Число дверей " + auto.doors);
    }

    /**
     * Ввод транспорта
     */
    static private void inputDefault() {
        displayInfo("Введите номер");
        String number = inputInfo();
        while (!number_pattern.matcher(number).matches()) {
            displayInfo("Неверный номер, введите другой");
            number = inputInfo();
        }
        displayInfo("Введите пробег");



    }

    /**
     * Ввод автомобиля
     */
    static public void inputAuto() {
        displayInfo("Введите параметры автомобиля");


    }
}

