package app.tests;


import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookingControllerTest {
    @Before
    public void setUp(){
        List<Passenger> p = new ArrayList<>(List.of(new Passenger("Vlad", "Ivanov")));
         Booking b = new Booking(0, p);
         b.setBookingTestID("testID_is_2021");

    }

    @Test
    public void deleteBookingById_Successful(String TestID ){

        boolean result = false;
        assertThat(result, is(false));
    }

}
