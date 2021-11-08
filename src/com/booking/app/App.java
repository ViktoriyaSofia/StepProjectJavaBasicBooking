package com.booking.app;

import com.booking.app.dao.BookingDaoFile;
import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.domain.console.Console;
import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;
import com.booking.app.services.BookingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking com.booking.app!");
        Console console = new Console();
//        console.run();



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