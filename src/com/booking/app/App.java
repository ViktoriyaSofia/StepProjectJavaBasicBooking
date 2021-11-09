package com.booking.app;

import com.booking.app.controllers.BookingController;
import com.booking.app.dao.BookingDaoFile;
import com.booking.app.domain.console.Console;
import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;
import com.booking.app.services.BookingService;

import java.io.IOException;

public class App {
    public static final String BOOKING_FILE_PATH_NAME = "./src/com/booking/app/DB/booking.bin";
    public static final String FLIGHT_FILE_PATH_NAME = "./src/com/booking/app/DB/flight.bin";

    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking com.booking.app!");
        Console console = new Console();
//        console.run();

        //инстанциируем сервисы: dao, service, controller для booking:
        BookingDaoFile bookingDao = new BookingDaoFile();
        BookingService bs = new BookingService(bookingDao);
        BookingController bc = new BookingController(bs);

        //создаём тестовые 2шт резервирования с flightID 15 и 27, по 4 пассажира в каждом
        bc.bookingInit();
        //выводим на экран резервирование для пассажира:
        // и удаляем резервирование заданного ID- номера:
        bc.bookingMethodsDemo();
    }
}