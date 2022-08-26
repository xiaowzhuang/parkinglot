package org.oobootcamp.parkinglot.parkingBoy;

import org.oobootcamp.parkinglot.Car;
import org.oobootcamp.parkinglot.ParkingLot;
import org.oobootcamp.parkinglot.Ticket;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;
import org.oobootcamp.parkinglot.parkingBoy.ParkingBoy;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getAvailableSpotsNumber() > 0).findFirst();
        if (availableParkingLot.isPresent()) {
            return availableParkingLot.get().park(car);
        }
        throw new ParkingLotFullException();
    }


}
