package app.dao;

import app.domain.flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightDao implements MainDao<Flight>{
    private final List<Flight> flights = new ArrayList<>();


    /** Override get methods  **/
    @Override
    public List<Flight> getAll() {
        return this.flights;
    }

    @Override
    public Flight getById(int id) {
        Flight flightById = (Flight) this.flights.stream()
                .filter(flight -> flight.getFlightID() == id);

        return flightById;
    }

    @Override
    public Flight getByIndex(int index) {
        if (index >= 0 && index < flights.size()){
            return this.flights.get(index);
        }else return null;
    }

    /** Override delete methods  **/
    @Override
    public Flight delete(int index) {
        if (index >= 0 && index < flights.size()){
            return this.flights.remove(index);
        }else return null;
    }

    @Override
    public boolean delete(Flight flight) {
        return this.flights.remove(flight);
    }

    /** Override save method  **/
    @Override
    public Flight save(Flight flight){
        int flightIndex = this.flights.indexOf(flight);

        if (this.flights.contains(flight)){
            this.flights.set(flightIndex, flight);
        }else {
            this.flights.add(flight);
        }

        return flight;
    }
}
