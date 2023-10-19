package com.niksahn.transport;
import java.util.Scanner;

public class Main {
    public  static UI ui  = new UI();

    public static void main(String[] args) {
        System.out.println("21ВП2 Бригада 5 Антонов и Новоженов");
        System.out.println("Вариант №5: Класс 'Реки' \n");
        Scanner in = new Scanner(System.in);
        Transport.set_transport_length(6);
        System.out.println("Вы хотите создать реку? (да или  нет)");
        String createRiverCheck = in.nextLine();
        while (!"нет".equals(createRiverCheck) &&
                !Transport.transport_Full()){
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


class UI{
   private Scanner in = new Scanner(System.in);
   private void displayInfo(String str){
        System.out.println(str);
    }
   private String inputInfo(){
        return in.nextLine();
   }

    public  void displayInfoMsg(){
       displayInfo("22ВП3 Бригада 3 Сахно и Новосельцев \n Вариант №3: Класс 'Транспорт' \n");
    }

    public void displayFullArrayMsg(){
       displayInfo("Массив заполнен");
    }

    public  void  displayTransportNotFound(){
       displayInfo("Такого транспорта нет!");
    }
}

