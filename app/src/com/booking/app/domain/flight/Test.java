package com.booking.app.domain.flight;

import java.io.InvalidObjectException;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws InvalidObjectException {
//      Пробую класс FlightGenerator >>>
        List<Flight> flightCollection = FlightGenerator.generateFlightCollection(50);
//        flightCollection.forEach(flight -> System.out.println(flight.prettyFormatFlight()));

        List<Flight> finalFlightCollection = flightCollection;
        Map<Integer, Flight> flightsCollectionMap = flightCollection.stream()
                .collect(Collectors.toMap(
                        i -> finalFlightCollection.indexOf(i)+1,
                        flight -> flight
                ));
        flightsCollectionMap.forEach( (integer, flight) -> System.out.printf("\n#%d. %s", integer, flight.prettyFormatFlight()));
    }
}