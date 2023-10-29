package com.niksahn.transport;

import static com.niksahn.transport.UI.displayAuto;

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

    public void change_fields(int change_field, String new_val) {
        super.change_fields(change_field, new_val);
        switch (change_field) {
            case 6:
                this.hand = new_val;
                break;
            case 7:
                this.doors = Integer.parseInt(new_val);
                break;
            case 8:
                this.body = new_val;
                break;
            case 9:
                this.transmission = new_val;
                break;
            case 10:
                this.drive = new_val;
                break;
        }
    }
}