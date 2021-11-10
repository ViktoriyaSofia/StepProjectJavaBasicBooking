package com.booking.app.dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    List<T> getALlCollection();
    T getByIndex(int index);
    T getById(int id);
    List<T> getCollectionFromDB() throws IOException;
    T saveCollectionToDB(T obj);
}
