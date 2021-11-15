package app.controllers;

import app.domain.flight.Flight;
import app.services.FlightService;

import java.util.List;

public class FlightController {
    public FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getFlightsFromDB() {
        return flightService.getFlightsFromDB();
    }

    public void showFlightsCollection(List<Flight> collection) {
        flightService.showFlightsCollection(collection);
    }

    public boolean saveFlightToDB (List<Flight> flight){
        return flightService.saveFlightToDB(flight);
    }

    public Flight getFlightById (int id) {
        return flightService.getFlightById(id);
    }

    public Flight getFlightByIndex(int index) {
        return flightService.getFlightByIndex(index);
    }

    public Flight createNewFlight(int flightID, String destination, long dateTime, int totalPlaces, int soldPlaces) {
        return flightService.createNewFlight(flightID, destination, dateTime, totalPlaces, soldPlaces);
    }

    public List<Flight> generateFlightDB(int flightsNumber, int nextDaysNumber) {
        return flightService.generateFlightDB(flightsNumber, nextDaysNumber);
    }

    public Flight updateFlight(Flight f) {
        return flightService.updateFlight(f);
    }

    public List<Flight> sortFlightsByDate(List<Flight> flights) {
        return flightService.sortFlightsByDate(flights);
    }

    public List<Flight> sortFlightsByDestination(List<Flight> flights){
        return flightService.sortFlightsByDestination(flights);
    }

    public List<Flight> getFlightsInOneDayPeriod () {
        return flightService.getFlightsInOneDayPeriod();
    }

    public List<Flight> findFlights (String destination, String date, int passengers) {
        return flightService.findFlights(destination, date, passengers);
    }

}