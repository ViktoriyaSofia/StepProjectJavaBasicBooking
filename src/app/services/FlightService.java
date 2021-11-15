package app.services;

import app.dao.FlightDaoFile;
import app.domain.dateMethods.DateMethods;
import app.domain.flight.Flight;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDaoFile flightDao;
    private final List<Flight> flightsList = new ArrayList<>();

    public FlightService(FlightDaoFile flightDao) {
        this.flightDao = flightDao;
    }


    public List<Flight> getFlightsFromDB() {
        return flightDao.retrieve();
    }

    public boolean saveFlightToDB(List<Flight> flights) {
        return flightDao.store(flights);
    }

    public Flight getFlightById(int id) {
        return flightDao.retrieveById(id);
    }

    public Flight getFlightByIndex(int index) {
        return flightDao.retrieveByIndex(index);
    }

    public Flight createNewFlight(int id, String destination, long dateTime, int totalPlaces, int soldPlaces) {
        Flight flight = new Flight(id, destination, dateTime, totalPlaces, soldPlaces);
        flightsList.add(flight);
        this.saveFlightToDB(flightsList);
        return flight;
    }


    public List<Flight> generateFlightDB(int flightsNumber, int nextDaysNumber) {

        for (int i = 0; i < flightsNumber; i++) {
            int flightId = generateRandomNumber(100000, 999999);
            String destination = generateDestination();
            long date = generateDateTime(nextDaysNumber);
            int totalPlaces = 40;
            int soldPlaces = generateRandomNumber(0, totalPlaces + 1);

            this.createNewFlight(flightId, destination, date, totalPlaces, soldPlaces);

        }

        return flightsList;
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    private String generateDestination() {
        String[] destinationCities = {
                "New York City", "Chicago", "Dallas", "Atlanta", "Miami",
                "Los Angeles", "San Diego", "San Francisco", "Long Beach", "San Jose",
                "Berlin", "Hamburg", "München", "Köln", "Frankfurt",
                "Paris", "Marseille", "Lyon", "Toulouse", "Nice",
                "Melbourne", "Sydney", "Brisbane", "Perth", "Adelaide",
                "Athens", "Thessaloniki", "Piraeus", "Larissa", "Heraklion",
                "Istanbul", "Ankara", "Izmir", "Bursa", "Antalya",
                "Tokyo", "Yokohama", "Osaka", "Nagoya", "Kyoto",
                "Hong Kong", "Macau", "Beijing", "Tianjin", "Chaohu",
                "Kharkiv", "Odesa", "Dnipro", "Lviv", "Kherson"
        };
        int randomCity = (int) (Math.random() * destinationCities.length);
        return destinationCities[randomCity];
    }

    public long generateDateTime(int maxDays) {
        DateMethods dm = new DateMethods();
        return dm.generateLocalDateTime(maxDays);
    }

    public Flight updateFlight(Flight f) {
        List<Flight> flights = new ArrayList<>(Collections.unmodifiableList(flightDao.retrieve()));

        int flightIndex = flights.indexOf(f);

        if (flights.contains(f)) {
            flights.set(flightIndex, f);
        } else {
            flights.add(f);
        }
        flightDao.store(flights);

        return f;
    }


    public List<Flight> sortFlightsByDate(List<Flight> flights) {
        return flights.stream()
                .sorted((f1, f2) -> (int) (f1.getDateSeconds() - f2.getDateSeconds()))
                .collect(Collectors.toList());
    }

    public List<Flight> sortFlightsByDestination(List<Flight> flights) {
        return flights.stream()
                .sorted(Comparator.comparing(Flight::getDestination))
                .collect(Collectors.toList());
    }

    public List<Flight> getFlightsInOneDayPeriod() {

        List<Flight> flights = Collections.unmodifiableList(this.getFlightsFromDB());

        List<Flight> flightsInOneDayPeriod = flights.stream()
                .filter(flight ->
                        LocalDateTime.ofEpochSecond(flight.getDateSeconds(), 0, ZoneOffset.UTC)
                                .isAfter(LocalDateTime.now()) &&
                                LocalDateTime.ofEpochSecond(flight.getDateSeconds(), 0, ZoneOffset.UTC)
                                        .isBefore(LocalDateTime.now().plusDays(1))
                ).collect(Collectors.toList());

        return this.sortFlightsByDate(flightsInOneDayPeriod);
    }

    /**
            * Поиск полётов по месту прибытию, дате и кол-ву пассажиров
     */

    public List<Flight> findFlights(String destination, String date, int passengers) {
        List<Flight> flights = Collections.unmodifiableList(this.getFlightsFromDB());

        return flights.stream()
                .filter(flight -> (
                        flight.getDestination().equals(destination) &&
                                flight.getDate().equals(date) &&
                                flight.getAvailablePlaces() >= passengers)
                ).collect(Collectors.toList());
    }

    //  Выводит в консоль коллекцию рейсов (всю или отфильтрованную часть)
    public void showFlightsCollection(List<Flight> collection) {
        int index = 1;
        for (Flight flight : collection) {
            System.out.printf("#%-3d", index);
            flight.prettyFormatFlight();
            index++;
        }
    }
}