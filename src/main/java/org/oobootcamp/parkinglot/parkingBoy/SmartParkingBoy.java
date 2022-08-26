package org.oobootcamp.parkinglot.parkingBoy;

import org.oobootcamp.parkinglot.Car;
import org.oobootcamp.parkinglot.ParkingLot;
import org.oobootcamp.parkinglot.Ticket;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        List<ParkingLot> ParkingLotList = this.parkingLots.stream().filter(parkingLot -> parkingLot.getAvailableSpotsNumber() > 0).toList();
        if (ParkingLotList.isEmpty()) {
            throw new ParkingLotFullException();
        }
       ParkingLot parkingLot = ParkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpotsNumber)).get();
        return parkingLot.park(car);
    }
}
