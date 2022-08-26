package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.parkingBoy.GraduateParkingBoy;
import org.oobootcamp.parkinglot.parkingBoy.SmartParkingBoy;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {
    @Test
    public void should_park_by_manager_when_park_given_1_parking_lot_and_no_boys() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot));
        Car car = new Car("京A12345");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLot.hasCar(ticket));
    }

    @Test
    public void should_park_by_manager_in_order_when_park_given_2_parking_lots_and_no_boys() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(asList(parkingLotA, parkingLotB));
        Car car = new Car("京A12345");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    public void should_park_by_manager_in_order_when_park_given_2_parking_lots_and_one_is_full() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        parkingLotA.park(new Car("京A12345"));
        ParkingManager parkingManager = new ParkingManager(asList(parkingLotA, parkingLotB));
        Car car = new Car("京A23456");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotB.hasCar(ticket));
    }

    @Test
    public void should_park_by_graduate_boy_when_park_given_graduate_boy_has_available_paring_lot() {
        ParkingLot parkingLotA = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotA));
        ParkingManager parkingManager = new ParkingManager(Collections.emptyList(), Collections.singletonList(graduateParkingBoy));
        Car car = new Car("京A12345");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    public void should_park_by_smart_boy_when_park_given_smart_boy_has_available_paring_lot() {
        ParkingLot parkingLotA = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        ParkingManager parkingManager = new ParkingManager(Collections.emptyList(), Collections.singletonList(smartParkingBoy));
        Car car = new Car("京A12345");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    public void should_park_by_boys_in_order_when_park_given_smart_boy_then_graduate_boy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotB));
        ParkingManager parkingManager = new ParkingManager(Collections.emptyList(), asList(smartParkingBoy, graduateParkingBoy));
        Car car = new Car("京A12345");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    public void should_park_by_boys_in_order_when_park_given_smart_boy_then_graduate_boy_and_smart_boy_has_full_lots() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotB));
        smartParkingBoy.park(new Car("京A12345"));
        ParkingManager parkingManager = new ParkingManager(Collections.emptyList(), asList(smartParkingBoy, graduateParkingBoy));
        Car car = new Car("京A23456");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotB.hasCar(ticket));
    }

    @Test
    public void should_park_by_parking_boy_when_park_given_graduate_boy_and_one_parking_lot() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotA));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotB), Collections.singletonList(graduateParkingBoy));
        Car car = new Car("京A23456");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotA.hasCar(ticket));
    }

    @Test
    public void should_park_by_manager_when_park_given_graduate_boy_with_full_parking_lot_and_one_parking_lot() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotA));
        graduateParkingBoy.park(new Car("京A12345"));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotB), Collections.singletonList(graduateParkingBoy));
        Car car = new Car("京A23456");

        Ticket ticket = parkingManager.park(car);

        assertEquals(ticket.getLicensePlateNumber(), car.getLicensePlateNumber());
        assertTrue(parkingLotB.hasCar(ticket));
    }

    @Test
    public void should_get_car_when_pick_up_given_valid_ticket_and_parking_manager_has_one_parking_lot_and_no_boys() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotA));
        Car car = new Car("京A23456");
        Ticket ticket = parkingLotA.park(car);

        Car actualCar = parkingManager.pickUp(ticket);

        assertEquals(car, actualCar);
    }

    @Test
    public void should_get_car_when_pick_up_given_valid_ticket_and_parking_manager_has_one_graduate_boy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotA));
        ParkingManager parkingManager = new ParkingManager(Collections.emptyList(), Collections.singletonList(graduateParkingBoy));
        Car car = new Car("京A23456");
        Ticket ticket = graduateParkingBoy.park(car);

        Car actualCar = parkingManager.pickUp(ticket);

        assertEquals(car, actualCar);
    }

    @Test
    public void should_get_car_when_pick_up_given_valid_ticket_and_parking_manager_has_boys_and_lots() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingLot parkingLotC = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotB));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotC), asList(smartParkingBoy, graduateParkingBoy));
        Car car = new Car("京A12345");
        Ticket ticket = parkingManager.park(car);

        Car actualCar = parkingManager.pickUp(ticket);

        assertEquals(car, actualCar);
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingLot parkingLotC = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotB));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotC), asList(smartParkingBoy, graduateParkingBoy));
        Ticket ticket = parkingManager.park(new Car("京A12345"));
        parkingManager.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(ticket));
    }

    @Test
    void should_get_error_message_when_pick_up_given_a_fake_ticket() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingLot parkingLotC = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLotA));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLotB));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLotC), asList(smartParkingBoy, graduateParkingBoy));
        Ticket ticket = new Ticket("京A12345");

        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(ticket));
    }
}
