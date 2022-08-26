package org.oobootcamp.parkinglot.parkingBoy;

import org.oobootcamp.parkinglot.Car;
import org.oobootcamp.parkinglot.ParkingLot;
import org.oobootcamp.parkinglot.Ticket;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;

import java.util.List;

public abstract class ParkingBoy {

    protected List<ParkingLot> parkingLots;

    public abstract Ticket park(Car car);

    public Car pickUp(Ticket ticket) {
        var actualParkingLot = parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasCar(ticket))
            .findAny()
            .orElseThrow(InvalidTicketException::new);
        return actualParkingLot.pickUp(ticket);
    }
}
