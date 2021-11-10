package com.booking.app.controllers;

import com.booking.app.domain.flight.Flight;
import com.booking.app.services.FlightService;

import java.util.List;

public class FlightController {
    public FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
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

    public int updateFlight(Flight flight) {
        return flightService.updateFlight(flight);
    }

    public List<Flight> sortFlightsByDate(List<Flight> flights) {
        return flightService.sortFlightsByDate(flights);
    }

    public List<Flight> getFlightsInOneDayPeriod () {
        return flightService.getFlightsInOneDayPeriod();
    }

    public List<Flight> findFlights (String destination, String date, int passengers) {
        return flightService.findFlights(destination, date, passengers);
    }

}
