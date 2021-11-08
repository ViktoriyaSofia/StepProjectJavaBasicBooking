package app.dao;

public interface Dao<T> {
    T retrieveById(int id);
    boolean updateById(int id);
    boolean deleteById(int id);
    T findAll();
    T findById(int id);
    boolean saveAll(String filename);
    boolean retrieveAll(String filename);
}
