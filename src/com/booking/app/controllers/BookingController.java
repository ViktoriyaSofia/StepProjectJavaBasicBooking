package com.booking.app.controllers;

import com.booking.app.dao.BookingDaoFile;
import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.services.BookingService;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    public void bookingInit() {
        BookingDaoFile bookingDao = new BookingDaoFile();
        BookingService bs = new BookingService(bookingDao);

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = bs.dao.retrieveAll();

        List<Passenger> pL = BookingService.createDefaultBookingList();
        Booking newBooking = bs.createNewBooking(15, pL);

        //saving booking to a DB file:
        storedBookings.add(newBooking);
        bs.dao.saveAll(new ArrayList<>(storedBookings));
    }
}
