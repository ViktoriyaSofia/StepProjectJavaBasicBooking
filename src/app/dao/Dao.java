package app.dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    List<T> retrieve() throws IOException;
    T store(T obj);
}
