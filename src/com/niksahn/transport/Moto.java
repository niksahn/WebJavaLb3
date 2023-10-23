package com.niksahn.transport;

import static com.niksahn.transport.Constants.*;
import static com.niksahn.transport.UI.displayMoto;
import static com.niksahn.transport.UI.inputField;

public class Moto extends Transport {
    /**
    * Тип мотоцикла
    */
    protected String type;

    /**
     * Количество колёс
     */
    protected Integer wheelsNum;

    /**
     * Возможность установки коляски
     */
    protected Boolean sidecarEnabled;

    /**
     * Наличие электростартера
     */
    protected Boolean electroStarter;

    /**
     * Тип топливной системы
     */
    protected String fuelSystem;

    @Override
    public void display_info() {
        displayMoto(this);
    }

    Moto(){
        super();
        this.type = null;
        this.electroStarter = null;
        this.wheelsNum = null;
        this.sidecarEnabled = null;
        this.fuelSystem = null;
    }

    Moto(String registration_number,
         int mileage,
         int price,
         int issueYear,
         String brand,
         String type,
         int wheelsNum,
         Boolean electroStarter,
         Boolean sidecarEnabled,
         String fuelSystem
    ) {
        super(registration_number, mileage, price, issueYear, brand);
        this.type = type;
        this.electroStarter = electroStarter;
        this.wheelsNum = wheelsNum;
        this.sidecarEnabled = sidecarEnabled;
        this.fuelSystem = fuelSystem;
    }

    public void change_fields(int change_field) {
        super.change_fields(change_field);
        switch (change_field) {
            case 6:
                this.type = inputField(string_pattern);
                break;
            case 7:
                this.electroStarter = Boolean.valueOf(inputField(boolean_pattern));
                break;
            case 8:
                this.wheelsNum = Integer.valueOf(inputField(string_pattern));
                break;
            case 9:
                this.sidecarEnabled = Boolean.valueOf(inputField(boolean_pattern));
                break;
            case 10:
                this.fuelSystem = inputField(string_pattern);
                break;
        }
    }
}
