package org.oobootcamp.parkinglot;

public class Car {
    private final String licensePlateNumber;
    public Car(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return this.licensePlateNumber;
    }
}
