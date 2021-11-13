package app.tests;

import app.controllers.FlightController;
import app.dao.FlightDaoFile;
import app.domain.flight.Flight;
import app.services.FlightService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightTest {
    final static FlightDaoFile flightDao = new FlightDaoFile("flightsTest.bin");
    final static FlightService flightService = new FlightService(flightDao);
    final static FlightController flightController = new FlightController(flightService);
    private final Flight f = flightController.createNewFlight(1000, "Madrid", 1636754187, 40, 30);

    @Test
    public void flightPrettyFormatTest() throws IOException {
        List<Flight> flights = flightController.getFlightsFromDB();
        String expected = flights.get(0).prettyFormatFlight();
        assertEquals(expected, f.prettyFormatFlight());
    }

    @Test
    public void flightPrettyFormatFullInfoTest() throws IOException {
        List<Flight> flights = flightController.getFlightsFromDB();
        String expected = flights.get(0).prettyFormatFlightFullInfo();
        assertEquals(expected, f.prettyFormatFlightFullInfo());
    }

    @Test
    public void flightToStringTest() throws IOException {
        List<Flight> flights = flightController.getFlightsFromDB();
        String expected = flights.get(0).toString();
        assertEquals(expected, f.toString());
    }
}