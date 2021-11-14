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
    FlightDaoFile fDao = new FlightDaoFile("flightsServiceTest.bin");
    FlightService fs = new FlightService(fDao);
    List<Flight> flights = new ArrayList<>();

    @Test
    public void getFlightsFromDBTest () {
        Flight f1 = fs.createNewFlight(1000, "Ankara", 1636754187, 40, 30);
        Flight f2 = fs.createNewFlight(1000, "Munich", 1636754187, 40, 30);
        flights.add(f1);
        flights.add(f2);
        assertEquals(fs.getFlightsFromDB(), flights);
    }

    @Test
    public void createNewFlightTest () {
        Flight f = fs.createNewFlight(1111, "Barcelona", 1636754187, 40, 30);
        assertEquals(f, fs.getFlightsFromDB().get(0));
    }

    @Test
    public void getFlightByIdTest () {
        Flight f = fs.createNewFlight(1111, "Barcelona", 1636754187, 40, 30);
        Flight actual = fs.getFlightById(f.getFlightID());
        assertEquals(f, actual);
    }

    @Test
    public void getFlightByIndexTest () {
        Flight f1 = fs.createNewFlight(2094, "Barcelona", 1636754187, 50, 9);
        Flight f2 = fs.createNewFlight(1055, "Madrid", 1636754187, 24, 2);
        Flight expected = fs.getFlightByIndex(1);
        assertEquals(expected, f2);
    }

    @Test
    public void generateFlightDBTest () throws IOException {
        List<Flight> fGen = fs.generateFlightDB(3, 1);
        List<Flight> fCol = fs.getFlightsFromDB();

        assertEquals(fGen, fCol);
    }
}
