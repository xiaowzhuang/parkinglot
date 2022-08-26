package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    void should_return_a_ticket_when_parking_given_parking_lot_has_available_spot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("京A12345");

        Ticket ticket = parkingLot.park(car);

        assertEquals(car.getLicensePlateNumber(), ticket.getLicensePlateNumber());
    }

    @Test
    void should_return_parking_lot_is_full_error_message_when_parking_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("京A12345");
        Car car = new Car("京A23456");
        parkingLot.park(car1);

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car));
    }

    @Test
    void should_return_a_car_when_pick_up_car_given_a_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("京A12345");
        Ticket ticket = parkingLot.park(car);

        Car pickUpCar = parkingLot.pickUp(ticket);

        assertEquals(car, pickUpCar);
    }

    @Test
    void should_return_invalid_ticket_error_message_when_pick_up_car_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("京A12345");
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_return_invalid_ticket_error_message_when_pick_up_car_given_a_fake_ticket() {
        ParkingLot parkingLot = new ParkingLot(5);
        Ticket ticket = new Ticket("京A12345");

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUp(ticket));
    }
}
