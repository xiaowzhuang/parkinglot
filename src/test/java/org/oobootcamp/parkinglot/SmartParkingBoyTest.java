package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;
import org.oobootcamp.parkinglot.parkingBoy.SmartParkingBoy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_return_car_when_pick_up_car_given_park_car_by_parking_lot_and_pick_up_car_by_smart_parking_boy() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("123");
        Ticket ticket = parkingLot.park(car);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));

        Car actualCar = smartParkingBoy.pickUp(ticket);
        assertEquals(car, actualCar);
    }
    @Test
    void should_park_in_A_and_receive_a_ticket_when_park_given_parking_lot_A_and_B_have_same_available_spots() {
        ParkingLot parkingLotA = new ParkingLot(3);
        ParkingLot parkingLotB = new ParkingLot(3);
        Car car = new Car("京A12345");
        List<ParkingLot> parkingLots = List.of(parkingLotA, parkingLotB);
        SmartParkingBoy spb = new SmartParkingBoy(parkingLots);

        Ticket ticket = spb.park(car);

        assertEquals(car.getLicensePlateNumber(), ticket.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    void should_park_in_B_and_receive_a_ticket_when_park_given_parking_lot_A_is_full_and_B_has_available_spots() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(3);
        SmartParkingBoy spb = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A23456");

        Ticket ticket = spb.park(car);

        assertEquals(car.getLicensePlateNumber(), ticket.getLicensePlateNumber());
        assertTrue(parkingLotB.hasCar(ticket));
    }

    @Test
    void should_receive_parking_lot_is_full_error_message_when_park_given_parking_lot_A_and_B_are_full() {
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLotA.park(new Car("京A12345"));
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotB.park(new Car("京A23456"));
        SmartParkingBoy spb = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A24567");

        assertThrows(ParkingLotFullException.class, () -> spb.park(car));
    }

    @Test
    void should_get_car_when_pick_up_given_a_valid_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy spb = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A12345");
        Ticket ticket = spb.park(car);

        Car pickUpCar = spb.pickUp(ticket);
        assertEquals(car, pickUpCar);
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy spb = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A12345");
        Ticket ticket = spb.park(car);
        spb.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> spb.pickUp(ticket));
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_fake_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy spb = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Ticket ticket = new Ticket("京A12345");

        assertThrows(InvalidTicketException.class, () -> spb.pickUp(ticket));
    }
}
