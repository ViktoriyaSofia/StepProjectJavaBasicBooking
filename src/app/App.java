package app;

import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.console.Console;
import app.exceptions.WrongInputDataException;
import app.services.BookingService;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
        Console console = new Console();
//        console.run();

        BookingDaoFile bookingDao = new BookingDaoFile();
        BookingService bs = new BookingService(bookingDao);
        BookingController bc = new BookingController(bs);
        bc.bookingInit();
        String IDofBookingToBeDeleted = ((Booking)bs.dao.retrieve().get(0)).bookingID;
        bc.deleteBookingById(IDofBookingToBeDeleted);
    }
}