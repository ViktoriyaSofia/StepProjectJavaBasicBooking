package app.domain.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Booking implements Serializable, Cloneable {

    private static int bookingIDCounter;
    private static void getCounterStartValue(){
        // обращение в ДАО и получение начального значения счетчика
        // int counterStart;
    }

    static {bookingIDCounter = 0;}

    private int flightID;
    public final int bookingID;
    private List<Passenger> pL = new ArrayList<>();
    private String dest;
    private LocalDate date;
    private int seats;

    {
        this.dest = null;
        this.date = null;
        this.seats = 1;
    }

    public Booking(){
    this.bookingID = bookingIDCounter++;
//    this.bookingID = counterStart + bookingIDCounter++;
    }

    public Booking(int flightID){
    this();
     this.flightID = flightID;
    }

    public Booking(int flightID, List<Passenger> pL){
        this();
        this.flightID = flightID;
        Collections.copy(this.pL, pL);
    }
    public Booking(int flightID, String dest, LocalDate date, List<Passenger> pL) {
        this.flightID = flightID;
        this.dest = dest;
        this.date = date;
        this.pL = pL.stream().collect(Collectors.toList());
//        this.pL = new ArrayList<>(pL);
        this.seats = pL.size();
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
