package app.tests;


import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    public void deleteBookingById_Successful(){
        Booking b1 = new Booking(0, p);
        b1.setBookingTestID("testID_is_2021");
        Booking b2 = new Booking(1, p);
        b2.setBookingTestID("testID_is_2022");
        this.bL.addAll(List.of(b1, b2));
        bc.bs.dao.store(bL);

        bc.deleteBookingById("testID_is_2021");
        List<Booking> result = bc.bs.dao.retrieve();
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getFlightID(), is(1));
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
        assertThat(result.size(), is(2));
    }


}
