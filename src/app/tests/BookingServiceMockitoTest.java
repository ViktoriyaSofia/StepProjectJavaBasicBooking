package app.tests;

import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class BookingServiceMockitoTest {


    @Mock
    private List<Passenger> p;
    private final BookingDaoFile dao;
    private final BookingService bs;

    @BeforeEach
    public void setUp(){
        p = new ArrayList<>(List.of(new Passenger("Ivan", "Ivanov")));
    }

    public BookingServiceMockitoTest(){
        MockitoAnnotations.initMocks(this);
        dao = new BookingDaoFile();
        bs = new BookingService(dao);
    }

    @Test
    public void getBookingByIdSuccessful(){
        given(bs.getAllBookingsFromFile().WillReturn(
                new ArrayList<>(List.of(new Booking(0, p)));
        ));
        List<Booking> bl =
    }
}
