package com.booking.app;

import com.booking.app.domain.console.Console;
import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
        Console console = new Console();
        console.run();

        createDefaultBookings();
    }
}