package app.dao;

import app.domain.flight.Flight;

import java.util.List;

public class FlightDaoFile extends AbstractDao<Flight> {

    public FlightDaoFile() {
        super("flights.bin");
    }

    @Override
    public List<Flight> retrieveAll() {
        return col;
    }

    @Override
    public Flight retrieveByIndex(int index) {
        if (index >= 0 && index < col.size()) {
            return this.col.get(index);
        } else return null;
    }

    @Override
    public Flight retrieveById(int id) {
        for (Flight flight : retrieveAll()){
            if (flight.getFlightID() == id){
                return flight;
            }
        }
        return null;
    }
}
