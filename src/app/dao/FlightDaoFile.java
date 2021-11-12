package app.dao;

import app.domain.flight.Flight;

import java.util.List;

public class FlightDaoFile extends AbstractDao<Flight> {

    public FlightDaoFile() {
        super("flights.bin");
    }

//    @Override
    public Flight retrieveByIndex(int index) {
        List<Flight> flights = retrieve();

        if (index >= 0 && index < flights.size()) {
            return flights.get(index);
        } else return null;
    }

//    @Override
    public Flight retrieveById(int id) {
        for (Flight flight : retrieve()){
            if (flight.getFlightID() == id){
                return flight;
            }
        }
        return null;
    }
}
