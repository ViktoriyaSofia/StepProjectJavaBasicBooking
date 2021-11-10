package com.booking.app.dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    T getById(int id);
    List<T> getAll();
    List<T> getCollectionFromDB() throws IOException;
    boolean saveAll(List<T> itemsList);
    T getByIndex(int index);
    T delete(int index);
    boolean delete(T obj);
    T save (T obj);
}


