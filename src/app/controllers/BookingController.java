package app.controllers;

import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingController {
    private final BookingService bs;

    public BookingController(BookingService service){
        this.bs = service;
    }

    /**
     * Инициализация и создание тестовых резервирований - используется только для проверки или демонстрации функционала букинг-контроллера
     * (после финального мерджа проекта, этот метод удалим)
     */
    public void bookingInit() {

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = Collections.unmodifiableList(bs.dao.getAll());
        System.out.println("из файла считано:  " + storedBookings.size() + " bookings.");
        List<Passenger> pL1 = BookingService.createPl1();
        List<Passenger> pL2 = BookingService.createPl2();
        Booking newBooking1 = bs.createNewBooking(15, pL1);
        Booking newBooking2 = bs.createNewBooking(27, pL2);

        List<Booking> updatedBookings = new ArrayList<>(storedBookings);
        updatedBookings.add(newBooking1);
        updatedBookings.add(newBooking2);

        bs.dao.saveAll(new ArrayList<>(updatedBookings));
        System.out.println("В файл записано: " + updatedBookings.size() + " bookings.");
    }



    /**
     * Только для демонстрации работы метода getAllBookingsByPassangerName
     */
    public void printBookingOfPineloppa(String name, String lName){
        Optional<List<Booking>> bOpt= bs.getAllBookingsByPassangerName(name, lName);
        if (bOpt.isPresent() && bOpt.get().size() != 0) {
            System.out.println("This passenger has the following booking(s):");
            System.out.println(bOpt.get());
        } else {
            System.out.println("This person does not have any bookings yet");
        }
    }

    /**
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
        System.out.println("All pending bookings: " + bs.dao.getAll());
    }
}
