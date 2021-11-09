package com.booking.app.services;

import com.booking.app.dao.BookingDaoFile;
import com.booking.app.domain.booking.Booking;
import com.booking.app.domain.booking.Passenger;
import com.booking.app.domain.flight.Flight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingService {
    public final BookingDaoFile dao;

    public BookingService(BookingDaoFile dao) {
        this.dao = dao;
    }

    /**
     * Генератор тестовых пассажиров для одного букинга:
     */
    public static List<Passenger> createPl1() {
        return new ArrayList<>(List.of(
                new Passenger("Ivan", "Petrov"),
                new Passenger("Pineloppa", "Zdurovskaya")
        ));
    }
    public static List<Passenger> createPl2() {
        return new ArrayList<>(List.of(
                new Passenger("Armageddon", "Fioletovich"),
                new Passenger("Nelya", "Sidorova")
        ));
    }

    /**
     * Это основной метод создания резервирований (booking):
     */
    public Booking createNewBooking(int flightID, List<Passenger> passenger) {
        Booking b = new Booking(flightID, "LA",
                LocalDate.of(2021, 11, 30), 2);
//              LocalDate.parse("20:09:2021", DateTimeFormatter.ofPattern("dd:MM:yyyy");
        b.setpL(passenger);
        return b;
    }

    public void cancelBookingById(int id) {
        List<Booking> bL = dao.retrieveAll();
        System.out.println("number of bookings before delition: " + bL.size());
        List<Booking> newBL = bL.stream().filter(el -> {
            System.out.println("processing booking number: " + el.getBookingID());
            return el.getBookingID() != id;
        }).collect(Collectors.toList());
//        List<Booking> newBL = bL.stream().filter(el -> el.getBookingID() != id).collect(Collectors.toList());
        bL = newBL; //перезапись ссылки bL обязательна, т.к. bL постоянно ссылается на dao.col,
        // который будет считан в файл по dao.saveAll() ниже:
        dao.saveAll();
        System.out.println("booking number " + id + "removed");
        System.out.println("number of bookings after delition: " + newBL.size());
    }

    /**
     * ЭТо метод производит поиск по имени и фамилии пассажира и возвращает список всех резервирований (bookings) данного пассажира.
     * Для получения номера рейса первого эл-та возвращаемого списка, вызываем метод:
     * getAllBookingsByPassangerName("Иван", "Петров").get(0).getFlightID
     */
    public Optional<List<Booking>> getAllBookingsByPassangerName(String name, String lastName) {
        List<Booking> bL = dao.retrieveAll();
        List<Booking> updatedL =  bL.stream().flatMap(el -> {
                    List<Passenger> locaPassangerlList = el.getpL();
                    if (
                            locaPassangerlList.stream().anyMatch(passanger -> {
                                        return passanger.getName().equalsIgnoreCase(name)
                                                && passanger.getlName().equalsIgnoreCase(lastName);
                                    }
                            )) {
                        return Stream.of(el);
                    } else {
                        return Stream.empty();
                    }

                }).collect(Collectors.toList());

        return Optional.of(updatedL);
    }
}
