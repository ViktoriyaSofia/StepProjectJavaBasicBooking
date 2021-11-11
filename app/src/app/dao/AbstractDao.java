package app.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    List<T> col = new ArrayList<>();

    String fileName;
    public AbstractDao() {}

    public AbstractDao(String fileName) {
        this.fileName = fileName;
    }


    /** получение коллекций (booking | flight) в зависимости от типа - T и fileName - название файла где лежат эти коллекции **/
    public List<T> retrieve()  {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ){
            col = (List<T>) ois.readObject();
        }catch (ClassNotFoundException error){
            System.out.println("Клас не найден");
        }catch (IOException  error){
            System.out.println("Ошибка при чтении файла booking.bin или flight.bin");
            System.out.println(error.getMessage());
        }

        return col;
    }

    /**
     * @param collectionObject и  - объект типа Flight или Booking
     * @return возвращает T - Flight или Booking
     **/

    public T store(T collectionObject) {
        int collectionIndex = this.col.indexOf(collectionObject);

        if (this.col.contains(collectionObject)){
            this.col.set(collectionIndex, collectionObject);
        }else {
            this.col.add(collectionObject);
        }

        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);)
        {
            oos.writeObject(this.col);
        }catch (IOException error){
            error.printStackTrace(System.out);
        }

        return collectionObject;
    }

    public abstract List<T> retrieveAll();
    public abstract T retrieveByIndex(int index);
    public abstract T retrieveById(int id);
}
