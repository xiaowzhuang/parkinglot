package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;
import org.oobootcamp.parkinglot.parkingBoy.GraduateParkingBoy;
import org.oobootcamp.parkinglot.parkingBoy.SmartParkingBoy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {
    @Test
    void should_park_in_A_and_receive_a_ticket_when_park_given_parking_lot_A_and_B_have_available_spots() {
        ParkingLot parkingLotA = new ParkingLot(3);
        ParkingLot parkingLotB = new ParkingLot(5);
        Car car = new Car("京A12345");
        List<ParkingLot> parkingLots = List.of(parkingLotA, parkingLotB);
        GraduateParkingBoy gpb = new GraduateParkingBoy(parkingLots);

        Ticket ticket = gpb.park(car);

        assertEquals(car.getLicensePlateNumber(), ticket.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    void should_park_in_B_and_receive_a_ticket_when_park_given_parking_lot_A_is_full_and_B_has_available_spots() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(5);
        parkingLotA.park(new Car("京A12345"));
        GraduateParkingBoy gpb = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A23456");

        Ticket ticket = gpb.park(car);

        assertEquals(car.getLicensePlateNumber(), ticket.getLicensePlateNumber());
        assertTrue(parkingLotB.hasCar(ticket));
    }

    @Test
    void should_receive_parking_lot_is_full_error_message_when_park_given_parking_lot_A_and_B_are_full() {
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLotA.park(new Car("京A12345"));
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotB.park(new Car("京A23456"));
        GraduateParkingBoy gpb = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A24567");

        assertThrows(ParkingLotFullException.class, () -> gpb.park(car));
    }

    @Test
    void should_return_car_when_pick_up_car_given_park_car_by_parking_lot_and_pick_up_car_by_graduate_parking_boy() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("123");
        Ticket ticket = parkingLot.park(car);

        GraduateParkingBoy smartParkingBoy = new GraduateParkingBoy(List.of(parkingLot));

        Car actualCar = smartParkingBoy.pickUp(ticket);
        assertEquals(car, actualCar);
    }

    @Test
    void should_get_car_when_pick_up_given_a_valid_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy gpb = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A12345");
        Ticket ticket = gpb.park(car);

        Car pickUpCar = gpb.pickUp(ticket);

        assertEquals(car, pickUpCar);
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy gpb = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("京A12345");
        Ticket ticket = gpb.park(car);
        gpb.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> gpb.pickUp(ticket));
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_fake_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy gpb = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Ticket ticket = new Ticket("京A12345");

        assertThrows(InvalidTicketException.class, () -> gpb.pickUp(ticket));
    }
}
