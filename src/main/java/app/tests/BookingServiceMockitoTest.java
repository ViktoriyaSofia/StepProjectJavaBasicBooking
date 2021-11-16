package app.tests;

import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.services.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookingServiceMockitoTest {

    private List<Passenger> pL;

    @Mock
    private final BookingDaoFile dao;
    private final BookingService bs;

    @BeforeEach
    public void setUp(){
        pL = new ArrayList<>(List.of(new Passenger("Ivan", "Ivanov"),
                new Passenger("Fedor", "Petrov")));
    }

    public BookingServiceMockitoTest(){
        MockitoAnnotations.openMocks(this);
        dao = Mockito.mock(BookingDaoFile.class);
        bs = new BookingService(dao);
    }

    @Test
    public void getBookingByIdSuccessful(){
        Booking b1 = new Booking(2);
        b1.setpL(pL);
        List<Booking> bLfromStorage = new ArrayList<>(List.of(b1, new Booking(0, pL) ));

        Mockito.when(dao.retrieve()).thenReturn(bLfromStorage);
        List<Booking> bl = bs.getAllBookingsFromFile();
        assertNotNull(bl);
        assertEquals("Petrov", bl.get(0).getpL().get(1).getlName());

    }
}
