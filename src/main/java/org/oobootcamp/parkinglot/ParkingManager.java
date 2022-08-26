package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.ParkingLotFullException;
import org.oobootcamp.parkinglot.parkingBoy.ParkingBoy;

import java.util.List;

public class ParkingManager {

    private List<ParkingLot> parkingLots;

    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }

    public Ticket park(Car car) {
        if (parkingBoys != null) {
            return parkingBoysParkCar(car);
        }
        return managerParkCar(car);
    }

    private Ticket managerParkCar(Car car) {
        List<ParkingLot> availableParkingLots = parkingLots.stream().filter(parkingLot -> parkingLot.getAvailableSpotsNumber() > 0).toList();
        if (availableParkingLots.isEmpty()) {
            throw new ParkingLotFullException();
        }
        return availableParkingLots.get(0).park(car);
    }

    private Ticket parkingBoysParkCar(Car car) {
        Ticket ticket;
        for (ParkingBoy parkingBoy : parkingBoys) {
            parkingBoy.park(car);
        }
        return null;
    }
}
