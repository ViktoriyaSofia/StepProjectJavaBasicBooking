package app.controllers;

import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingController {
<<<<<<< HEAD
    public final BookingService bs;
=======
    private final BookingService bs;
>>>>>>> dev

    public BookingController(BookingService service){
        this.bs = service;
    }

    /**
     * Инициализация и создание тестовых резервирований - используется только для проверки или демонстрации функционала букинг-контроллера
     * (после финального мерджа проекта, этот метод удалим)
     */
    public void bookingInit() {

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = Collections.unmodifiableList(bs.dao.retrieve());
        System.out.println("из файла считано:  " + storedBookings.size() + " bookings.");
        List<Passenger> pL1 = BookingService.createPl1();
        List<Passenger> pL2 = BookingService.createPl2();
<<<<<<< HEAD
        Booking newBooking1 = createBooking(15, pL1);
        Booking newBooking2 = createBooking(27, pL2);
=======
        Booking newBooking1 = bs.createNewBooking(15, pL1);
        Booking newBooking2 = bs.createNewBooking(27, pL2);
>>>>>>> dev

        List<Booking> updatedBookings = new ArrayList<>(storedBookings);
        updatedBookings.add(newBooking1);
        updatedBookings.add(newBooking2);

        bs.dao.store(new ArrayList<>(updatedBookings));
        System.out.println("В файл записано: " + updatedBookings.size() + " bookings.");
    }

<<<<<<< HEAD
    /**
     * Получить резервирования из файла/DB
     */
    public List<Booking> retrieveAllBookings(){
        return bs.getAllBookingsFromFile();
    }
=======
>>>>>>> dev


    /**
     * Только для демонстрации работы метода getAllBookingsByPassangerName
     */
<<<<<<< HEAD
    public void printBookingOfGivenPassenger(String name, String lName){
=======
    public void printBookingOfPineloppa(String name, String lName){
>>>>>>> dev
        Optional<List<Booking>> bOpt= bs.getAllBookingsByPassangerName(name, lName);
        if (bOpt.isPresent() && bOpt.get().size() != 0) {
            System.out.println("This passenger has the following booking(s):");
            System.out.println(bOpt.get());
        } else {
            System.out.println("This person does not have any bookings yet");
        }
    }

    /**
<<<<<<< HEAD
     *   главный Метод создания резервирований
     */
    public Booking createBooking(int flightID, List<Passenger> passenger){
        return bs.createNewBooking(flightID, passenger);
    }


    /**
=======
>>>>>>> dev
     * Для использования в проекте
     */
    public void deleteBookingById(String bookingId){
        System.out.println("we are deleting now boooking with ID: " + bookingId);
        bs.cancelBookingById(bookingId);
    }

    /**
     * Для использования в проекте
     */
    public void printAllBookings(){
        System.out.println("All pending bookings: " + bs.dao.retrieve());
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> dev
