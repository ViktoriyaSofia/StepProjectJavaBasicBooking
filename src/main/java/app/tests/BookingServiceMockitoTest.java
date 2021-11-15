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


    @Mock
    private List<Passenger> pL1, pL2;
    private final BookingDaoFile dao;
    private final BookingService bs;

    @BeforeEach
    public void setUp(){
        Passenger p1 = new Passenger("Ivan", "Ivanov");
        Passenger p2 = new Passenger("Fedor", "Petrov");
        pL1 = new ArrayList<>(List.of(p1));
        pL2 = new ArrayList<>(List.of(p2));
    }

    public BookingServiceMockitoTest(){
        MockitoAnnotations.openMocks(this);
        dao = Mockito.mock(BookingDaoFile.class);
        bs = new BookingService(dao);
    }

    @Test
    public void getBookingByIdSuccessful(){
        List<Booking> bLfromStorage = new ArrayList<>(List.of(new Booking(0, pL1),
                new Booking(2, pL2)));
        Mockito.when(dao.retrieve()).thenReturn(bLfromStorage);
        List<Booking> bl = bs.getAllBookingsFromFile();
        assertNotNull(bl);
        assertEquals("Petrov", bl.get(0).getpL().get(1).getlName());

    }
}
