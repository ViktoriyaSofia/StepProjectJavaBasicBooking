package com.booking.app;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {

        System.out.println("Welcome to the best flight booking app!");


        Passenger p1 = new Passenger("Ivan", "Petrov");
        Passenger p2 = new Passenger("Pineloppa", "Zdurovskaya");
        Passenger p3 = new Passenger("Armageddon", "Fioletovich");

        Booking b1 = new Booking(15, "LA",
                LocalDate.of(2021, 11, 30), 2);
        b1.getpL().add(p1);

        Booking b2 = new Booking(17, "NY",
                LocalDate.parse("20:09:2021", DateTimeFormatter.ofPattern("dd:MM:yyyy")), 3);

        b1.getpL().add(p2);
        b2.getpL().add(p3);

        System.out.println(b1);
        System.out.println(b2);
    }
}
