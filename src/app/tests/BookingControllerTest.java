package app.tests;

import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookingControllerTest {

    List<Passenger> p;
    BookingController bc;
    List<Booking> bL = new ArrayList<>();

    @Before
    public void setUp(){
        this.p = new ArrayList<>(List.of(new Passenger("Ivan", "Ivanov")));

        BookingDaoFile dao = new BookingDaoFile();
        BookingService bs = new BookingService(dao);
        this.bc = new BookingController(bs);
    }

    @Test
    public void createBookingNotNUllSuccess(){
        Booking b = bc.createBooking(0, p);
        assertNotNull(b);
        assertEquals(0, b.getFlightID());
        assertEquals("Ivan", b.getpL().get(0).getName());
    }
    @Test
    public void createBookingSuccess(){
        Booking b = bc.createBooking(0, p);
        assertEquals(0, b.getFlightID());
        assertEquals("Ivan", b.getpL().get(0).getName());
    }
    @Test
    public void createBookingUnSuccess(){
        Booking b = bc.createBooking(0, null);
        assertNull(b);
    }


    @Test
    public void deleteBookingById_Successful(){
        Booking b1 = new Booking(0, p);
        b1.setBookingTestID("testID_is_2021");
        Booking b2 = new Booking(1, p);
        b2.setBookingTestID("testID_is_2022");
        this.bL.addAll(List.of(b1, b2));
        bc.bs.dao.store(bL);

        int flightID = bc.deleteBookingById("testID_is_2021");
        List<Booking> result = bc.bs.dao.retrieve();
        MatcherAssert.assertThat(result.size(), is(1));
        MatcherAssert.assertThat(result.get(0).getFlightID(), is(1));
        assertEquals(0, flightID);
    }

    @Test
    public void deleteBookingById_WithArgNullSuccessful(){
        Booking b1 = new Booking(0, p);
        b1.setBookingTestID("testID_is_2021");
        Booking b2 = new Booking(1, p);
        b2.setBookingTestID("testID_is_2022");
        this.bL.addAll(List.of(b1, b2));
        bc.bs.dao.store(bL);
        bc.deleteBookingById(null);
        List<Booking> result = bc.bs.dao.retrieve();
        MatcherAssert.assertThat(result.size(), is(2));
    }
}
