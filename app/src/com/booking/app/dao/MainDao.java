package com.booking.app.dao;

import java.util.List;

public interface MainDao<T> {
    T getById(int id);
    List<T> getAll();
    T getByIndex(int index);

    T delete(int index);
    boolean delete(T obj);

    T save (T obj);
}
