package app.domain.flight;

import app.domain.dateMethodsw.DateMethods;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Flight implements Serializable {
    private int flightID = -1;
    private String destination = "";
    private long dateSeconds = -1;
    private String date = "";
    private String time = "";

    private int totalPlaces = -1;
    private int soldPlaces = -1;
    private int availablePlaces = -1;

    public Flight(){
    }
    public Flight(int id, String destination, long dateSeconds, int totalPlaces, int soldPlaces) {
        this.flightID = id;
        this.destination = destination;
        this.dateSeconds = dateSeconds;
        this.date = getDateStringFromEpochSecond(dateSeconds);
        this.time = getTimeStringFromEpochSecond(dateSeconds);
        this.totalPlaces = totalPlaces;
        this.soldPlaces = soldPlaces;
        setAvailablePlaces();
    }

    public int getFlightID() {
        return flightID;
    }
    public void setFlightID(int id) {
        this.flightID = id;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getDateSeconds() {
        return dateSeconds;
    }
    public void setDateSeconds(long dateSeconds) {
        this.dateSeconds = dateSeconds;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }
    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public int getSoldPlaces() {
        return soldPlaces;
    }
    public void setSoldPlaces(int soldPlaces) {
        this.soldPlaces = soldPlaces;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }
    public void setAvailablePlaces() {
        this.availablePlaces = this.getTotalPlaces() - this.getSoldPlaces();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return getFlightID() == flight.getFlightID() && getDateSeconds() == flight.getDateSeconds()
                && getDestination().equals(flight.getDestination());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getFlightID(), getDestination(), getDateSeconds());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", destination='" + destination + '\'' +
                ", dateDeparture='" + date + '\'' +
                ", timeDeparture='" + time + '\'' +
                ", totalPlaces=" + totalPlaces +
                ", soldPlaces=" + soldPlaces +
                ", availablePlaces=" + availablePlaces +
                '}';
    }

    public void prettyFormatFlight() {
        String format = "%-22s%-30s%s%n";
        String flight = "\tFlight: " + " ID='" + (this.getFlightID() == -1 ? "no info" : this.getFlightID()) + "'";
        String destination = "destination='" + (this.getDestination().equals("") ? "no info" : this.getDestination()) + "'";
        String dateDeparture =  "dateDeparture='" + (this.getDateSeconds() == -1 ? "no info"
                : getDateTimeStringFromEpochSecond(this.getDateSeconds())) + "'";
        System.out.printf(format, flight, destination, dateDeparture);
    }

    public void prettyFormatFlightFullInfo() {
        String format = "%-21s%-26s%-42s%n%-21s%-26s%s%n";
        String flight = "Flight: " + "ID='" + (this.getFlightID() == -1 ? "no info" : this.getFlightID()) + "'";
        String destination = "destination='" + (this.getDestination().equals("") ? "no info" : this.getDestination()) + "'";
        String dateDeparture =  "dateDeparture='" + (this.getDateSeconds() == -1 ? "no info"
                : getDateTimeStringFromEpochSecond(this.getDateSeconds())) + "'";
        String totalPlaces = "totalPlaces='" + this.getTotalPlaces() + "'";
        String soldPlaces = "soldPlaces='" + this.getSoldPlaces() + "'";
        String availablePlaces = "availablePlaces='" + this.getAvailablePlaces() + "'";
        System.out.printf(format, flight, destination, dateDeparture, totalPlaces, soldPlaces, availablePlaces);
    }


    //  Метод getDateTimeStringFromEpochSecond() - принимает long EpochSecondOfDay
    //  - возвращает LocalDateTime date(полученное из принятой long EpochSecondOfDay)
    //    дату и время в строке формата "dd/MM/yyyy hh:mm:ss a" (например: "04/11/2021 03:16:57 PM")
    private String getDateTimeStringFromEpochSecond(long l){
        DateMethods dateMethods = new DateMethods();
        LocalDateTime dateTime = dateMethods.convertEpochSecondOfDayToLocalDateTime(l);
        return dateMethods.formatLocalDateTimeToStringDateTime(dateTime);
    }


    //  Метод getDateStringFromEpochSecond() - принимает long EpochSecondOfDay
    //  - возвращает LocalDateTime date(полученное из принятой long EpochSecondOfDay)
    //    только дату в строке формата "dd/MM/yyyy" (например: "04/11/2021")
    private String getDateStringFromEpochSecond(long l){
        DateMethods dateMethods = new DateMethods();
        LocalDateTime dateTime = dateMethods.convertEpochSecondOfDayToLocalDateTime(l);
        return dateMethods.formatLocalDateTimeToStringDate(dateTime);
    }


    //  Метод getDateStringFromEpochSecond() - принимает long EpochSecondOfDay
    //  - возвращает LocalDateTime date(полученное из принятой long EpochSecondOfDay)
    //    только время в строке формата "hh:mm:ss a" (например: "03:16:57 PM")
    private String getTimeStringFromEpochSecond(long l){
        DateMethods dateMethods = new DateMethods();
        LocalDateTime dateTime = dateMethods.convertEpochSecondOfDayToLocalDateTime(l);
        return dateMethods.formatLocalDateTimeToStringTime(dateTime);
    }

}