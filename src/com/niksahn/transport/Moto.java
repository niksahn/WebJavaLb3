package com.niksahn.transport;


import static com.niksahn.transport.Main.ui;

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
    public void display_info(UI ui) {
        ui.display(this.toString());
    }

    Moto() {
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

    public void change_fields(int change_field, String new_val) {
        super.change_fields(change_field, new_val);
        switch (change_field) {
            case 6:
                this.type = new_val;
                break;
            case 7:
                this.electroStarter = Boolean.valueOf(new_val);
                break;
            case 8:
                this.wheelsNum = Integer.valueOf(new_val);
                break;
            case 9:
                this.sidecarEnabled = Boolean.valueOf(new_val);
                break;
            case 10:
                this.fuelSystem = new_val;
                break;
        }
    }

    @Override
    public String toString() {
        String dopInf = "";
        if (this.electroStarter) dopInf += "Электростартер\n";
        if (this.sidecarEnabled) dopInf += "Возможность установки коляски\n";
        return "Мотоцикл \n" + super.toString() + "Тип " + this.type + "\n" + "Число колёс " + this.wheelsNum + "\n"
                + "Топливная система " + this.fuelSystem + "\n" + dopInf;
    }

}
