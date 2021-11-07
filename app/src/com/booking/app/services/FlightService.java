package com.booking.app.services;

import com.booking.app.dao.FlightDao;
import com.booking.app.domain.flight.Flight;

import java.util.List;

public class FlightService {
    private final FlightDao flightDao;

    /** FightService constructor **/
    private FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    /** Get methods **/
    public List<Flight> getAllFlights() {
        return flightDao.getAll();
    }

    public Flight getFlightById(int id) {
        return flightDao.getById(id);
    }

    public Flight getFlightByIndex(int index) {
        return flightDao.getByIndex(index);
    }

    /** Delete methods **/
    private Flight deleteFlightByIndex (int index) {
        return flightDao.delete(index);
    }

    private boolean deleteFlight (Flight flight) {
        return flightDao.delete(flight);
    }

    /** Save method **/
    private Flight createNewFlight (int id, String destination, long dateSeconds, int totalPlaces, int soldPlaces) {
        Flight flight = new Flight(id, destination, dateSeconds, totalPlaces, soldPlaces);
        return flightDao.save(flight);
    }


}
