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
            col.clear();
            col = (List<T>) ois.readObject();
        }catch (ClassNotFoundException error){
            System.out.println("Клас не найден");
        }catch (IOException  error){
            System.out.println("Ошибка при чтении файла booking.bin или flight.bin. Check and make sure the files exist in the root directory");
            System.out.println(error.getMessage());
        }

        return col;
    }

    /**
     *  Получает список itemsList (это flights или bookings), копирует в col,
     *  и вызывается метод дао сохранения col. в файл
     * @param itemsList  - List типа Flight или Booking
     * @return boolean (true/false)
     **/

    public boolean store(List<T> itemsList) {
        col.clear();
        col.addAll(itemsList);
        boolean success = writeToFile();
        col.clear();
        if (success) {
            System.out.println("all items saved");
            return true;
        } else {
            System.out.println("Ошибка в сохранении");
            return false;
        }
    }

    public boolean writeToFile() {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(col);
        } catch (IOException e) {
            System.out.println("Error writing to files: booking.bin or flight.bin. Please, contacting your software developer to fix the problem");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
