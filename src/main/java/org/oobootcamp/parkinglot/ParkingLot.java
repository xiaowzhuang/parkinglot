package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;

    private final Map<Ticket, Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new HashMap<>(capacity);
    }

    public Ticket park(Car car) {
        if (parkedCars.size() == capacity) {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket(car.getLicensePlateNumber());
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        Car car = parkedCars.get(ticket);
        if (car != null) {
            parkedCars.remove(ticket);
            return car;
        }
        throw new InvalidTicketException();
    }

    public int getAvailableSpotsNumber() {
        return capacity - parkedCars.size();
    }

    public boolean hasCar(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }
}
