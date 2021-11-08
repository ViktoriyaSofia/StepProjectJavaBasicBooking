package app.dao;

import app.domain.booking.Booking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoFile extends a_Dao<Booking> {
    List<Booking> col = new ArrayList<>();
    public void saveAllBookings(List<Booking> bL, String bookingsFileName){
        col.addAll(bL);
        boolean success = saveAll(bookingsFileName);
        if (success) {
            System.out.println("all bookings saved");
        } else {
            System.out.println("error saving all bookings");
        }
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
