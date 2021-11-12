package app.dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    List<T> retrieve();
    boolean store(List<T> list);
}