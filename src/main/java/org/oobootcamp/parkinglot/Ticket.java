package org.oobootcamp.parkinglot;

public class Ticket {
    private final String licensePlateNumber;

    public Ticket(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return this.licensePlateNumber;
    }
}
