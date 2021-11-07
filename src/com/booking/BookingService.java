package com.booking;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    public static void createDefaultBooking() {
        List<Passenger> passenger = new ArrayList<>(List.of(
                new Passenger("Ivan", "Petrov"),
                new Passenger("Pineloppa", "Zdurovskaya"),
                new Passenger("Armageddon", "Fioletovich"),
                new Passenger("Nelya", "Sidorova")
                ));


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

    public Booking createNewBooking(int flightID, List<Passenger> passenger) {

    }
}
