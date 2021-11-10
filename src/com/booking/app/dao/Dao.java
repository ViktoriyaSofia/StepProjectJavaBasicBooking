package com.booking.app.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);
    List<T> getAll();
    boolean saveAll(List<T> itemsList);
    T getByIndex(int index);
    T delete(int index);
    boolean delete(T obj);
    T save (T obj);
}
