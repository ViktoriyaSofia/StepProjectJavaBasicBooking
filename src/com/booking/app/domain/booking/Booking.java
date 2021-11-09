package com.booking.app.domain.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable, Cloneable {

    private static int bookingIDCounter;


    private int flightID;
    public final int bookingID;
    private List<Passenger> pL = new ArrayList<>();
    private final String dest;
    private final LocalDate date;
    private int seats;

static {bookingIDCounter = 0;}


    public Booking(int flightID, String dest, LocalDate date, int seats) {
        this.flightID = flightID;
        this.dest = dest;
        this.date = date;
        this.seats = seats;
        this.bookingID = bookingIDCounter++;
    }

    @Override
    public String toString(){
        return  "\nBooked for flight: "+ this.flightID +
                "\n-> Booking number " + bookingID + "\n" +
                "\t destination:\t" + this.dest +
                "\t date:\t" + date.format(DateTimeFormatter.ofPattern("dd:MM:yyyy")) +
                "\t booked " + seats + " seats in this booking.\n" +
                "-> Passengers:\n" + pL + "\n";
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

    public int getBookingID(){return this.bookingID;}



    @Override
    public Booking clone() {
        try {
            Booking clone = (Booking) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
