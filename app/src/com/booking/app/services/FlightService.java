package com.booking.app.services;

import com.booking.app.dao.FlightDao;
import com.booking.app.domain.dateMethods.DateMethods;
import com.booking.app.domain.flight.Flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDao flightDao;

    private FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    /** Методы из FlightDao **/

    public List<Flight> getAllFlights() {
        return flightDao.getALlCollection();
    }

    private List<Flight> getFlightCollectionFromDB () throws IOException {
        return flightDao.getCollectionFromDB();
    }

    public Flight getFlightById(int id) {
        return flightDao.getById(id);
    }

    public Flight getFlightByIndex(int index) {
        return flightDao.getByIndex(index);
    }

    /** - Создание полёта **/
    public Flight createNewFlight (int id, String destination, long dateTime, int totalPlaces, int soldPlaces) {
        Flight flight = new Flight(id, destination, dateTime, totalPlaces, soldPlaces);
        return flightDao.saveCollectionToDB(flight);
    }


    /** Генерация список Flight - ов, запись в базу данных (файл) + доп. методы**/
    public List<Flight> generateFlightDB (int flightsNumber, int nextDaysNumber) {
        for (int i = 0; i < flightsNumber; i++) {
            int flightId = generateRandomNumber(1000, 9999);
            String destination = generateDestination();
            long date = generateDateTime(nextDaysNumber);
            int totalPlaces = 40;
            int soldPlaces = generateRandomNumber(0, totalPlaces + 1);

            this.createNewFlight(flightId, destination, date, totalPlaces, soldPlaces);
        }

        return this.getAllFlights();
    }

    private int generateRandomNumber(int min, int max){
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
                "Istanbul", "Ankara", "İzmir", "Bursa", "Antalya",
                "Tokyo", "Yokohama", "Osaka", "Nagoya", "Kyoto",
                "Hong Kong", "Macau", "Beijing", "Tianjin", "Chaohu",
                "Kharkiv", "Odesa", "Dnipro", "Lviv", "Kherson"
        };
        int randomCity = (int) (Math.random() * destinationCities.length + 1);
        return destinationCities[randomCity];
    }

    private long generateDateTime (int maxDays) {
        DateMethods dm = new DateMethods();
        return dm.generateLocalDateTime(maxDays);
    }

    /** Обновление или перезапись Flight **/
    public int updateFlight(Flight flight){

        List<Flight> flights = this.getAllFlights();
        int flightIndex = flights.indexOf(flight);

        if (flights.contains(flight)){
            flights.set(flightIndex, flight);
        }else {
            flights.add(flight);
        }

        return flights.indexOf(flight);
    }

    /** Сортировка полёта по дате **/
    public List<Flight> sortFlightsByDate (List<Flight> flights) {
        return flights.stream()
                .sorted((f1, f2) -> (int) (f1.getDateSeconds() - f2.getDateSeconds()))
                .collect(Collectors.toList());
    }

    /** Получение полётов в течении 24 часов и их стортировка **/
    public List<Flight> getFlightsInOneDayPeriod () {

        List<Flight> flightsInOneDayPeriod = this.getAllFlights().stream()
                .filter(flight ->
                        LocalDateTime.ofEpochSecond(flight.getDateSeconds(), 0, ZoneOffset.UTC)
                                .isAfter(LocalDateTime.now()) &&
                        LocalDateTime.ofEpochSecond(flight.getDateSeconds(), 0, ZoneOffset.UTC)
                                .isBefore(LocalDateTime.now().plusDays(1))
                ).collect(Collectors.toList());

        return this.sortFlightsByDate(flightsInOneDayPeriod);
    }
    /** Поиск полётов по месту прибытию, дате и кол-ву пассажиров **/
    public List<Flight> findFlights(String destination, String date, int passengers){
        return this.getAllFlights().stream()
                .filter(flight -> (
                    flight.getDestination().equals(destination) &&
                    flight.getDate().equals(date) &&
                    flight.getAvailablePlaces() >= passengers)
                ).collect(Collectors.toList());
    }
}
