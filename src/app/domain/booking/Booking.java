package app.domain.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable {
    private static int bookingIDCounter;


    private int flightID;
    private final int bookingID;
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
