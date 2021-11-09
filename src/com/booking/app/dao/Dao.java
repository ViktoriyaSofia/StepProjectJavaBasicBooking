package com.booking.app.dao;

import java.util.List;

public interface Dao<T> {
    T retrieveById(int id);
    boolean updateById(int id);
    boolean deleteById(int id);
    T findAll();
    T findById(int id);
    boolean saveAll();
    List<T> retrieveAll();
}
