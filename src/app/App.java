package app;

import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.domain.console.Console;
import app.domain.console.wrongInputDataException.WrongInputDataException;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
        Console console = new Console();
        console.run();

        List<Passenger> pL = BookingService.createDefaultBooking();
        BookingService BS = new BookingService();
        Booking newB = BS.createNewBooking(15, pL);
    }
}