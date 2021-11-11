package app.domain.flight;

import java.util.*;
import app.domain.dateMethodsw.DateMethods;


public class FlightGenerator {

    //  Метод generateFlightCollection() - генерирует и возвращает коллекцию рейсов List<Flight> flightCollection в кол-ве numberOfFlights
    public static List<Flight> generateFlightCollection(int numberOfFlights, int forNextNumberDays){
        List<Flight> flightCollection = new ArrayList<>();
        int count = 0;
        while (count < numberOfFlights){
            int flightID = generateInteger(1000, 9000);

            String destination = generateDestination();

            long dateSeconds = generateLocalDateTime(forNextNumberDays);

            int totalPlaces = 20;

            int soldPlaces = generateInteger(0, 21);

            Flight flight = new Flight(flightID, destination, dateSeconds, totalPlaces, soldPlaces);
            flightCollection.add(flight);
            count++;
        }
        return flightCollection;
    }


    //  Метод generateDestination() - генерирует и возвращает Destination - город направления рейса
    private static String generateDestination(){
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
                "Kharkiv", "Odesa", "Dnipro", "Lviv", "Kherson"};
        int indDestination = (int) (Math.random() * 50);
        return destinationCities[indDestination];

    }


    //  Метод generateInteger() - генерирует и возвращает целое число oт min до max
    private static int generateInteger(int min, int max){
        return (int) (min + (Math.random() * max));
    }


    //  Метод generateLocalDateTime() - генерирует и возвращает long epochSecondOfDay
    //  в период [от текущей LocalDateTime.now() до LocalDateTime.now() + принятое кол-во дней]
    private static long generateLocalDateTime(int days){
        DateMethods dateMethods = new DateMethods();
        return dateMethods.generateLocalDateTime(days);
    }

}