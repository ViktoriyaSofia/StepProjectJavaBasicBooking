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
        if (passenger == null) {
            System.out.println("passenger list is empty!");
            return null;
        };
        Booking b = new Booking(flightID, passenger);
        b.setpL(passenger);
        List<Booking> storedBookings = Collections.unmodifiableList(dao.retrieve());
        List<Booking> updatedBookings = new ArrayList<>(storedBookings);
        updatedBookings.add(b);
        dao.store(new ArrayList<>(updatedBookings));
        return b;
    }

    /**
     * Получить все резервирования из dao
     */
    public List<Booking> getAllBookingsFromFile(){
        return dao.retrieve();
    }

    /**
     * Метод для удаления резервирования по заданному String айдишнику.   Айдишники придется брать
     * из самих резервирований по:   bs.dao.getAll().get(0).bookingID;  (см. в App:  IDofBookingToBeDeleted  )
     */
    public int cancelBookingById(String id) {
        if (id == null) return -1;
        List<Booking> bL = Collections.unmodifiableList(dao.retrieve());

        Optional<Integer> cancelBookingFlightID = bL.stream().filter(b -> b.bookingID.equals(id))
                .map(Booking::getFlightID).findFirst();

        List<Booking> newBL = bL.stream().filter(el -> !el.getBookingID().equals(id)).collect(Collectors.toList());
        dao.store(newBL);
        System.out.println("booking number " + id + " removed");
        System.out.println("number of bookings after deletion: " + newBL.size());
        return cancelBookingFlightID.orElse(-1);
    }

    /**
     * ЭТо метод производит поиск по имени и фамилии пассажира и возвращает список всех резервирований (bookings) данного пассажира.
     * Для получения номера рейса первого эл-та возвращаемого списка, вызываем метод:
     * getAllBookingsByPassangerName("Иван", "Петров").get(0).getFlightID
     */
    public Optional<List<Booking>> getAllBookingsByPassangerName(String name, String lastName) {
        List<Booking> pendingBookings = Collections.unmodifiableList(dao.retrieve());
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
