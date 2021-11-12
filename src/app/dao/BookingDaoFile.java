package app.dao;

import app.domain.booking.Booking;

public class BookingDaoFile extends AbstractDao<Booking> {

    public BookingDaoFile(){
        super("booking.bin");
    }
}
