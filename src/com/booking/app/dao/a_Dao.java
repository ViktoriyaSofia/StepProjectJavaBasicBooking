package com.booking.app.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class a_Dao<T> implements Dao<T> {
    List<T> col = new ArrayList<T>();

    /**
     * метод сохранения коллекций col (flights, либо bookings) в файлах.  Имена файлов задаются параметром filename:
     */
    public boolean saveAll(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(col);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * метод получения коллекций col (flights, либо bookings) из файлов.  Имена файлов задаются параметром filename:
     */
    public boolean retrieveAll(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            try {
                col = (List<T>)ois.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * метод создает очередную новую запись объекта типа Т. Аргументом метода является либо
     * flight, либо booking.
     * Вызов метода в контроллере выполняется не через .dao , а через абстрактный  .a_dao , т.к. в интерфейсе не возможна типизация дженериком
     */
    public boolean createOne(T t) {
        col.add(t);
        return true;
    }

    public boolean updateById(int id) {
        return false;
    }

    public boolean deleteById(int id) {
        return false;
    }

}

