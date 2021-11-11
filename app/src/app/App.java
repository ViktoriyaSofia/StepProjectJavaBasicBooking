package app;


import app.controllers.FlightController;
import app.dao.FlightDaoFile;
import app.domain.flight.Flight;
import app.services.FlightService;

import java.io.IOException;
import java.util.List;

public class App {
    public static FlightDaoFile flightDao = new FlightDaoFile("flights.bin");
    public static FlightService flightService = new FlightService(flightDao);
    public static FlightController flightController = new FlightController(flightService);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
    }
}