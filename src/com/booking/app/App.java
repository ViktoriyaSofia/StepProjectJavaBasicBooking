package com.booking.app;

import com.booking.app.controllers.BookingController;
import com.booking.app.domain.console.Console;
import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking com.booking.app!");
        Console console = new Console();
//        console.run();

        BookingController bc = new BookingController();
        bc.bookingInit();

    }
}