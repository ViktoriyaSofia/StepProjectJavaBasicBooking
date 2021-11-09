package com.booking.app.controllers;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.services.BookingService;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private final BookingService bs;

    public BookingController(BookingService service){
        this.bs = service;
    }

    public void bookingInit() {

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = bs.dao.retrieveAll();

        List<Passenger> pL = BookingService.createDefaultBookingList();
        Booking newBooking1 = bs.createNewBooking(15, pL);
        Booking newBooking2 = bs.createNewBooking(27, pL);

        //saving booking to a DB file:
        storedBookings.add(newBooking1);
        storedBookings.add(newBooking2);
        bs.dao.saveAll(new ArrayList<>(storedBookings));
        System.out.println("Созданы 2 flights: 15 и 27, по 4 пассажира в каждом");
    }

    public void bookingMethodsDemo(){
        printBookingsOfPineloppaZdurovskaya("Pineloppa", "Zdurovskaya");
        deleteBooking15();
    }

    public void printBookingsOfPineloppaZdurovskaya(String name, String lName){
        bs.getAllBookingsByPassangerName(name, lName);
    }
    public void deleteBooking15(){
        bs.cancelBookingById(15);
    }

}
