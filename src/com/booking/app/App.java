package com.booking.app;

import com.booking.app.domain.booking.Booking;

public class App {
    public static void main(String[] args) {

        System.out.println("Welcome to the best flight booking app!");

        Booking.createDefaultBookings();
    }

}
