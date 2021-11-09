package com.booking.app.dao;

import com.booking.app.domain.flight.Flight;

import java.io.*;
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
    public List<Flight> getCollectionFromDB () throws IOException {
        List<Flight> flights = new ArrayList<>();

        try (
                FileInputStream fis = new FileInputStream("flights.txt");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ){
            flights = (List<Flight>) ois.readObject();
            List<Flight> finalFlights = flights;

            /** здесь должен быть flight.prettyFormat **/

            flights.forEach(flight -> {
                System.out.println(finalFlights.indexOf(flight) + 1 + ". " + flight.prettyFormatFlight());
            });
        }catch (FileNotFoundException | ClassNotFoundException error){
            error.printStackTrace(System.out);
        }

        return flights;
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

    /** Override save method  **/
    @Override
    public Flight save(Flight flight){
        int flightIndex = this.flights.indexOf(flight);

        if (this.flights.contains(flight)){
            this.flights.set(flightIndex, flight);
        }else {
            this.flights.add(flight);
        }

        try(FileOutputStream fos = new FileOutputStream("flights.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);)
        {
            oos.writeObject(this.flights);
        }catch (IOException error){
            error.printStackTrace(System.out);
        }

        return flight;
    }
}
