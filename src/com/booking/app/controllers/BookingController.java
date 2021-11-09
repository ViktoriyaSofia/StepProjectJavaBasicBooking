package com.booking.app.controllers;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.services.BookingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingController {
    private final BookingService bs;

    public BookingController(BookingService service){
        this.bs = service;
    }

    public void bookingInit() {

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = Collections.unmodifiableList(bs.dao.retrieveAll());
        System.out.println("из файла считано:  " + storedBookings.size() + " bookings.");
        List<Passenger> pL1 = BookingService.createPl1();
        List<Passenger> pL2 = BookingService.createPl2();
        Booking newBooking1 = bs.createNewBooking(15, pL1);
        Booking newBooking2 = bs.createNewBooking(27, pL2);

        List<Booking> updatedBookings = new ArrayList<>(storedBookings);
        updatedBookings.add(newBooking1);
        updatedBookings.add(newBooking2);

        bs.dao.saveAll(new ArrayList<>(updatedBookings));
        System.out.println("В файл записано: " + updatedBookings.size() + " bookings.");
    }

    public void bookingMethodsDemo(){
        printBookingOfPineloppa("Pineloppa", "Zdurovskaya");
        deleteBookingById(0);
//        printBookingOfPineloppa("Pineloppa", "Zdurovskaya");
//        printAllBookings();
    }

    public void printBookingOfPineloppa(String name, String lName){
        Optional<List<Booking>> bOpt= bs.getAllBookingsByPassangerName(name, lName);
        if (bOpt.isPresent() && bOpt.get().size() != 0) {
            System.out.println("This passenger has the following booking(s):");
            System.out.println(bOpt.get());
        } else {
            System.out.println("This person does not have any bookings yet");
        }
    }
    public void deleteBookingById(int bookingId){
        bs.cancelBookingById(bookingId);
    }

    public void printAllBookings(){
        System.out.println("All pending bookings: " + bs.dao.retrieveAll());
    }
}
