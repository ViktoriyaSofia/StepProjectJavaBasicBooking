package app.dao;

import java.util.List;

public interface Dao<T> {
    List<T> retrieve();
    T store(T obj);
}
