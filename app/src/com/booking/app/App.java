package com.booking.app;


import com.booking.app.controllers.FlightController;
import com.booking.app.dao.FlightDaoFile;
import com.booking.app.services.FlightService;

import java.io.IOException;

public class App {
    public static FlightDaoFile flightDao = new FlightDaoFile();
    public static FlightService flightService = new FlightService(flightDao);
    public static FlightController flightController = new FlightController(flightService);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
    }
}