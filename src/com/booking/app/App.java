package com.booking.app;

import com.booking.BookingService;
import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;

import java.util.List;

public class App {
    public static void main(String[] args) {

        System.out.println("Welcome to the best flight booking app!");

        List<Passenger> pL = BookingService.createDefaultBooking();
        BookingService BS = new BookingService();
        Booking newB = BS.createNewBooking(15, pL);
    }

}
