package app.domain.flight;

import app.domain.dateMethods.DateMethods;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Flight implements Serializable {
    private int flightID;
    private String destination = "";
    private long dateSeconds = -1;
    private String date = "";
    private String time = "";

    private int totalPlaces = -1;
    private int soldPlaces = -1;
    private int availablePlaces = -1;

    public Flight(){
        this.flightID = generateFlightId();
    }

    public Flight(int id, String destination, long dateSeconds, int totalPlaces, int soldPlaces) {
        this.flightID = id;
        this.destination = destination;
        this.dateSeconds = dateSeconds;
        this.date = getDateStringFromEpochSecond(dateSeconds);
        this.time = getTimeStringFromEpochSecond(dateSeconds);
        this.totalPlaces = totalPlaces;
        this.soldPlaces = soldPlaces;
        this.availablePlaces = setAvailablePlaces();
    }

//    - принимает строку датаВремя формата "dd/MM/yyyy hh:mm:ss a" ( например: "04/11/2021 03:16:57 PM" или "04/11/2021 03:16:57 AM")
    public Flight(int id, String destination, String dateTime, int totalPlaces, int soldPlaces) {
        this.flightID = id;
        this.destination = destination;
        this.dateSeconds = getLocalDateTimeToEpochSecondFromStringDateTime(dateTime);
        this.date = getDateStringFromEpochSecond(this.dateSeconds);;
        this.time = getTimeStringFromEpochSecond(this.dateSeconds);;
        this.totalPlaces = totalPlaces;
        this.soldPlaces = soldPlaces;
        this.availablePlaces = setAvailablePlaces();
    }

//    - принимает строку дата формата "dd/MM/yyyy" ("04/11/2021"), строку время формата "hh:mm:ss a" ("03:16:57 PM") или ("03:16:57 AM")
    public Flight(int id, String destination, String date, String time, int totalPlaces, int soldPlaces) {
        this.flightID = id;
        this.destination = destination;
        this.dateSeconds = getLocalDateTimeToEpochSecondFromDateTimeStrings(date, time);
        this.date = date;
        this.time = time;
        this.totalPlaces = totalPlaces;
        this.soldPlaces = soldPlaces;
        this.availablePlaces = setAvailablePlaces();
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
    public int setAvailablePlaces() {
        return getTotalPlaces() - getSoldPlaces();
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

    public String prettyFormatFlight() {
        return "\tFlight: " +
            "flightID='" + (this.getFlightID() == -1 ? "no info" : this.getFlightID()) + '\'' +
            ", destination='" + (this.getDestination().equals("") ? "no info" : this.getDestination()) +'\'' +
            ", dateDeparture='" + (this.getDateSeconds() == -1 ? "no info" : getDateTimeStringFromEpochSecond(this.getDateSeconds())) + '\'' +
            ", totalPlaces=" + totalPlaces +
            ", soldPlaces=" + soldPlaces +
            ", availablePlaces=" + availablePlaces +
            ";\n";
    }



//  Метод generateFlightId() - генерирует и возвращает ID рейса
    private int generateFlightId(){
        return (int) (Math.random() * 9001);
    }


//  Методы по конвертированию разных вариантов принятых данных по дате (для конструкторов Flight()) >>>
    //  Метод getLocalDateTimeToEpochSecondFromStringDateTime()
    //  - принимает строку датаВремя формата "dd/MM/yyyy hh:mm:ss a" (например: "04/11/2021 03:16:57 PM")
    //  - возвращает LocalDateTime date (полученное из принятой строки) в long epochSecondOfDay
    private long getLocalDateTimeToEpochSecondFromStringDateTime(String dateAndTime){
        DateMethods dateMethods = new DateMethods();
        LocalDateTime dateTime = dateMethods.getLocalDateTimeFromStringDateTime(dateAndTime);
        return dateMethods.convertLocalDateTimeToEpochSecond(dateTime);
    }


    //  Метод getLocalDateTimeToEpochSecondFromDateTimeStrings()
    //  - принимает строку дата формата "dd/MM/yyyy" ("04/11/2021")
    //  - принимает строку время формата "hh:mm:ss a" ("03:16:57 PM") или ("03:16:57 AM")
    //  - возвращает LocalDateTime date(полученное из принятых строк) в long epochSecondOfDay
    private long getLocalDateTimeToEpochSecondFromDateTimeStrings(String date, String time){
        DateMethods dateMethods = new DateMethods();
        LocalDateTime dateTime = dateMethods.getLocalDateTimeFromDateAndTimeStrings(date, time);
        return dateMethods.convertLocalDateTimeToEpochSecond(dateTime);
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