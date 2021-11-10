package com.booking.app.dao;

import com.booking.app.App;
import com.booking.app.domain.booking.Booking;

public class BookingDaoFile extends a_Dao<Booking> {

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
