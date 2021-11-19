package app.domain.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

public class Booking implements Serializable, Cloneable, Comparable<Booking> {

    private int flightID;
    public String bookingID;
    private List<Passenger> pL = new ArrayList<>();
    private String dest;
    private LocalDate date;
    private int seats;

    {
        this.dest = null;
        this.date = null;
        this.seats = -1;
        bookingID = UUID.randomUUID().toString().replace("-", "");
    }

    public Booking(){
    }

    public Booking(int flightID){
        this();
        this.flightID = flightID;
    }

    public Booking(int flightID, List<Passenger> pL){

        this.flightID = flightID;
        this.pL = pL.stream().collect(Collectors.toList());
    }
    public Booking(int flightID, String dest, LocalDate date, List<Passenger> pL) {
        this();
        this.flightID = flightID;
        this.dest = dest;
        this.date = date;
        this.pL = pL.stream().collect(Collectors.toList());
        this.pL = pL;
        this.seats = pL.size();
    }

    @Override
    public String toString(){
        return  "\nBooked for flight: "+ this.flightID +
                "\n-> Booking ID: " + bookingID + "\n" +
//                "\t destination:\t" + this.dest +
//                "\t date:\t" + date.format(DateTimeFormatter.ofPattern("dd:MM:yyyy")) +
//                "\t booked " + seats + " seats in this booking.\n" +
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

    public String getBookingID(){return this.bookingID;}

    public void setBookingTestID(String testID){this.bookingID = testID;}


    @Override
    public int compareTo(Booking o){
        return this.date.compareTo(o.date);
    }
        public int compareToAlternative(Booking o){
        if (this.date.isBefore(o.date)){
            return 1;
        } else if (this.date.isEqual(o.date)){
            return 0;
        } else return -1;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return  true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        };
        Booking objUnderCheck = (Booking) o;
        if ( objUnderCheck.getBookingID().equals(this.bookingID)) {
            {
                return true;
            }
        } else return false;
    }

    @Override
    public int hashCode(){
    return Objects.hash(this.bookingID);
    }


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
