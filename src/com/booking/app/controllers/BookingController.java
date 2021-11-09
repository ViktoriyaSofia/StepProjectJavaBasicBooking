package com.booking.app.controllers;

import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.services.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController {
    private final BookingService bs;

    public BookingController(BookingService service){
        this.bs = service;
    }

    public void bookingInit() {

        //retrieving bookings from a DB file:
        List<Booking> storedBookings = bs.dao.retrieveAll(); //ВНИМАНИЕ! storedBookings теперь ссылается на dao.col !!!
        System.out.println("из файла считано:  " + storedBookings.size() + " bookings.");
        List<Passenger> pL1 = BookingService.createPl1();
        List<Passenger> pL2 = BookingService.createPl2();
        Booking newBooking1 = bs.createNewBooking(15, pL1);
        Booking newBooking2 = bs.createNewBooking(27, pL2);

        //след.добавления делать НЕ НАДО, т.к. storedBookings является ссылкой на dao.col !
        storedBookings.add(newBooking1);    storedBookings.add(newBooking2);

        System.out.println("перед записью в файл: " + storedBookings.size() + " bookings.");
        bs.dao.saveAll(new ArrayList<>(storedBookings));
        System.out.println("В файл записано: " + storedBookings.size() + " bookings.");
    }

    public void bookingMethodsDemo(){
//        printBookingsOfPineloppaZdurovskaya("Pineloppa", "Zdurovskaya");
//        deleteBookingById();
    }

    public void printBookingsOfPineloppaZdurovskaya(String name, String lName){
        Optional<List<Booking>> bOpt= bs.getAllBookingsByPassangerName(name, lName);
        if (bOpt.isPresent()) {
            System.out.println("This person has at least one booking. See details below:");
            System.out.println(bOpt.get());
        } else {
            System.out.println("This person does not have any bookings yet");
        }
    }
    public void deleteBookingById(){
        bs.cancelBookingById(3);
    }

}
