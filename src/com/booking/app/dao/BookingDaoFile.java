package com.booking.app.dao;

import com.booking.app.App;
import com.booking.app.domain.booking.Booking;

public class BookingDaoFile extends a_Dao<Booking> {

    public BookingDaoFile(){
        super(App.BOOKING_FILE_PATH_NAME);
    }

    public Booking retrieveById(int id) {
        Booking b = null;
        return b;
    }
    public Booking findAll() {
        Booking t = null;
        return t;
    }

    public Booking findById(int id) {
        Booking t = null;
        return t;
    }




}
