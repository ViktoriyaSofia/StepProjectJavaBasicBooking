package app.dao;

import app.domain.booking.Booking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoFile extends a_Dao<Booking> {

    public BookingDaoFile(){
        super("./src/app/DB/booking.bin");
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
