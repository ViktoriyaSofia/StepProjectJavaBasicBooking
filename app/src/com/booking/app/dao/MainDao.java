package com.booking.app.dao;

import java.io.IOException;
import java.util.List;

public interface MainDao<T> {
    T getById(int id);
    List<T> getAll();
    List<T> getCollectionFromDB() throws IOException;
    T getByIndex(int index);
    T save (T obj);

}
