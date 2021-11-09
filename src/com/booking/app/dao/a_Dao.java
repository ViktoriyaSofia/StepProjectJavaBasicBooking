package com.booking.app.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class a_Dao<T> implements Dao<T> {
    List<T> col = new ArrayList<T>();
    String fileName;

    public a_Dao(){}
    public a_Dao(String fileName){
        this.fileName = fileName;
    }

    /**
     * saveAll()  получает список itemsList (это flights или bookings), копирует в col,
     * и вызывается метод дао saveAll() сохранения col. в файл
     */
    public boolean saveAll(List<T> itemsList){
//        col.addAll(itemsList);
        boolean success = writeToFile();
        if (success) {
            System.out.println("all items saved");
            return true;
        } else {
            System.out.println("error saving all items");
            return false;
        }
    }


    public boolean writeToFile() {
        try (FileOutputStream fos = new FileOutputStream(fileName);
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
    public List<T> retrieveAll() {
            try (FileInputStream fis = new FileInputStream(fileName);
                 ObjectInputStream ois = new ObjectInputStream(fis);)
            {
                col.addAll( (List<T>)ois.readObject() );
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException");
            } catch (IOException e) {
            System.out.println("IOException");
        }
        return col; //вне зависимости от успешности считывания файла (есть он или нет) - коллекция col должна существовать ,хоть и пустая!
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

