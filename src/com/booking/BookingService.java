package com.booking;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    public BookingService(){}

    public static List<Passenger> createDefaultBooking() {
        return  new ArrayList<>(List.of(
                new Passenger("Ivan", "Petrov"),
                new Passenger("Pineloppa", "Zdurovskaya"),
                new Passenger("Armageddon", "Fioletovich"),
                new Passenger("Nelya", "Sidorova")
        ));

    }

    public Booking createNewBooking(int flightID, List<Passenger> passenger) {
        Booking b = new Booking(flightID, "LA",
                LocalDate.of(2021, 11, 30), 2);
//              LocalDate.parse("20:09:2021", DateTimeFormatter.ofPattern("dd:MM:yyyy");
        b.setpL(passenger);
        System.out.println("a new booking's just been created: ");
        System.out.println(b);
        return b;
    }
}
