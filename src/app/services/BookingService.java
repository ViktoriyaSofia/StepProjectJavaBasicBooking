package app.services;

import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
//        Booking b = new Booking(flightID, "LA",
//                LocalDate.of(2021, 11, 30), 2);
//              LocalDate.parse("20:09:2021", DateTimeFormatter.ofPattern("dd:MM:yyyy");
        Booking b = new Booking(flightID, passenger);
        b.setpL(passenger);
        return b;
    }

    public void cancelBookingById(String id) {
        List<Booking> bL = Collections.unmodifiableList(dao.getAll());
        List<Booking> newBL = bL.stream().filter(el -> !el.getBookingID().equals(id)).collect(Collectors.toList());
        dao.saveAll(newBL);
        System.out.println("booking number " + id + " removed");
        System.out.println("number of bookings after deletion: " + newBL.size());
    }

    /**
     * ЭТо метод производит поиск по имени и фамилии пассажира и возвращает список всех резервирований (bookings) данного пассажира.
     * Для получения номера рейса первого эл-та возвращаемого списка, вызываем метод:
     * getAllBookingsByPassangerName("Иван", "Петров").get(0).getFlightID
     */
    public Optional<List<Booking>> getAllBookingsByPassangerName(String name, String lastName) {
        List<Booking> pendingBookings = Collections.unmodifiableList(dao.getAll());
        List<Booking> updatedL =  pendingBookings.stream().flatMap(el -> {
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
