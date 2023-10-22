package com.niksahn.transport;

import static com.niksahn.transport.Constants.*;
import static com.niksahn.transport.UI.displayAuto;
import static com.niksahn.transport.UI.inputField;

public class Auto extends Transport {

    /**
     * Тип руля
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
        super();
        this.hand = null;
        this.transmission = null;
        this.body = null;
        this.drive = null;
        this.doors = null;
    }

    Auto(String registration_number,
         int mileage,
         int price,
         int issueYear,
         String brand,
         String hand,
         String transmission,
         String body,
         String drive,
         Integer doors
    ) {
        super(registration_number, mileage, price, issueYear, brand);
        this.body = body;
        this.doors = doors;
        this.hand = hand;
        this.transmission = transmission;
        this.drive = drive;
    }

    public void change_fields(int change_field) {
        super.change_fields(change_field);
        switch (change_field) {
            case 6:
                this.hand = inputField(hand_pattern);
                break;
            case 7:
                this.doors = Integer.parseInt(inputField(positive_int_pattern));
                break;
            case 8:
                this.body =  inputField(string_pattern);
                break;
            case 9:
                this.transmission = inputField(transmission_pattern);
                break;
            case 10:
                this.drive = inputField(drive_pattern);
                break;
        }
    }

}