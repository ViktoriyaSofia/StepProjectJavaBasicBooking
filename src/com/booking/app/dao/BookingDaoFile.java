package com.booking.app.dao;

import com.booking.app.domain.booking.Booking;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class BookingDaoFile extends a_Dao<Booking> {

    public void saveAllBookings(List<Booking> bL, String bookingsFileName){
        col.addAll(bL);
        boolean success = saveAll(bookingsFileName);
        if (success) {
            System.out.println("all bookings saved");
        } else {
            System.out.println("error saving all bookings");
        }
    }

}
