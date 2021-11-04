package com.booking.app.domain.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable {
    private static int bookingIDCounter;
    public static void createDefaultBookings() {

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

    private int flightID;
    private int bookingID;
    private List<Passenger> pL = new ArrayList<>();
    private final String dest;
    private final LocalDate date;
    private       int seats;

static {bookingIDCounter = 0;}


    public Booking(int flightID, String dest, LocalDate date, int seats) {
        this.flightID = flightID;
        this.dest = dest;
        this.date = date;
        this.seats = seats;
        bookingID = bookingIDCounter++;
    }

    @Override
    public String toString(){
        return  "\nBooked for flight: "+ this.flightID +
                "\n-> Booking number " + bookingID + "\n" +
                "\t destination:\t" + this.dest +
                "\t date:\t" + date.format(DateTimeFormatter.ofPattern("dd:MM:yyyy")) +
                "\t booked " + seats + " seats in this booking.\n" +
                "-> Passengers:\n" + pL;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public List<Passenger> getpL() {
        return pL;
    }

    public void setpL(List<Passenger> pL) {
        this.pL = pL;
    }

    public String getDest() {
        return dest;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
