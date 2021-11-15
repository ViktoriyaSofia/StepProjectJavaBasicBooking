package app.tests;

import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    static List<Passenger> p;
    static BookingDaoFile dao;
    static BookingService bs;
    static List<Booking> bL = new ArrayList<>();

    @BeforeAll
    public static void setUpAll(){
        dao = new BookingDaoFile();
        bs = new BookingService(dao);
    }
    @BeforeEach
    public void setUp(){
        p = new ArrayList<>(List.of(new Passenger("Ivan", "Ivanov")));
    }

    @Test
    void createNewBookingSuccessful() {
        Booking b = bs.createNewBooking(0, p);
        assertEquals(0, b.getFlightID());
        assertEquals("Ivan", b.getpL().get(0).getName());
    }
    @Test
    void createNewBookingFlightNull_Successful() {
        Booking b = bs.createNewBooking(0, null);
        assertNull(b);
    }

    @Test
    void cancelBookingByIdSuccessful() {
        System.out.println("p: " + p);
        Booking b1 = new Booking(0, p);
        b1.setBookingTestID("testID_is_2021");
        Booking b2 = new Booking(1, p);
        b2.setBookingTestID("testID_is_2022");
        bL.addAll(List.of(b1, b2));
        bs.dao.store(bL);

        bs.cancelBookingById("testID_is_2021");
        List<Booking> result = bs.dao.retrieve();
        MatcherAssert.assertThat(result.size(), is(1));
        MatcherAssert.assertThat(result.get(0).getFlightID(), is(1));
    }
}