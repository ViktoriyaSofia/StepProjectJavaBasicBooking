package app.dao;

import app.domain.booking.Booking;

public class BookingDaoFile extends AbstractDao<Booking> {

    public BookingDaoFile(){
        super("booking.bin");
    }

    public Booking getById(int id) {
        Booking b = null;
        return b;
    }

    public Booking findById(int id) {
        Booking t = null;
        return t;
    }



}
