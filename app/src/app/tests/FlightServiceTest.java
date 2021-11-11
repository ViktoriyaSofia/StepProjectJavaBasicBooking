package app.tests;

import app.dao.FlightDaoFile;
import app.domain.flight.Flight;
import app.services.FlightService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightServiceTest {
    FlightDaoFile flightDaoFile = new FlightDaoFile("flightsTest.bin");
    FlightService flightService = new FlightService(flightDaoFile);
    List<Flight> flights = new ArrayList<>();

    @Test
    public void getAllFlightsTest () {
        Flight f1 = flightService.createNewFlight(1000, "Ankara", 1636754187, 40, 30);
        Flight f2 = flightService.createNewFlight(1000, "Munich", 1636754187, 40, 30);
        flights.add(f1);
        flights.add(f2);
        assertEquals(flightService.getAllFlights(), flights);
    }

    @Test
    public void createNewFlightTest () {
        Flight f = flightService.createNewFlight(1111, "Barcelona", 1636754187, 40, 30);
        assertEquals(f, flightService.getAllFlights().get(0));
    }

    @Test
    public void getFlightByIdTest () {
        Flight f = flightService.createNewFlight(1111, "Barcelona", 1636754187, 40, 30);
        Flight actual = flightService.getFlightById(f.getFlightID());
        assertEquals(f, actual);
    }

    @Test
    public void getFlightByIndexTest () {
        Flight f1 = flightService.createNewFlight(2094, "Barcelona", 1636754187, 50, 9);
        Flight f2 = flightService.createNewFlight(1055, "Madrid", 1636754187, 24, 2);
        Flight expected = flightService.getFlightByIndex(1);
        assertEquals(expected, f2);
    }

    @Test
    public void generateFlightDBTest () throws IOException {
        List<Flight> f = flightService.generateFlightDB(3, 1);
        List<Flight> fCol = flightService.getFlightsFromDB();
        assertEquals(fCol, f);
    }
}
