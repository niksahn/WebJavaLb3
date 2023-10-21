package com.niksahn.transport;

import static com.niksahn.transport.UI.displayAuto;
import static com.niksahn.transport.UI.inputAuto;

public class Auto extends Transport {

    /**
     * Праворульная ли
     */
    protected String hand;

    /**
     * Тип трансмиссии
     */
    protected String transmission;

    /**
     * Тип кузова
     */
    protected String body;

    /**
     * Привод
     */
    protected String drive;

    /**
     * Количество дверей
     */
    protected Integer doors;


    @Override
    public void display_info() {
        displayAuto(this);
    }

    Auto() {
        inputAuto();
    }

    Auto(String registration_number,
         int mileage,
         int price,
         int issueYear,
         String type,
         String hand,
         String transmission,
         String body,
         String drive,
         Integer doors
    ) {
        super(registration_number, mileage, price, issueYear, type);
        this.body = body;
        this.doors = doors;
        this.hand = hand;
        this.transmission = transmission;
        this.drive = drive;
    }

    public void change_fields(int change_field, String new_field_value) {
        super.change_fields(change_field, new_field_value);
        switch (change_field) {
            case 6:
                this.hand = new_field_value;
                break;
            case 7:
                this.doors = Integer.parseInt(new_field_value);
                break;
            case 8:
                this.body = new_field_value;
                break;
            case 9:
                this.transmission = new_field_value;
                break;
            case 10:
                this.drive = new_field_value;
                break;
        }
    }

}