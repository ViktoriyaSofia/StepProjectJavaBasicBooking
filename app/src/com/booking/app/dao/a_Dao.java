package com.booking.app.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class a_Dao<T> implements Dao<T> {

    List<T> col = new ArrayList<>();

    String fileName;
    public a_Dao() {}

    public a_Dao(String fileName) {
        this.fileName = fileName;
    }

    /**
     * saveAll()  получает список itemsList (это flights или bookings), копирует в col,
     * и вызывается метод дао saveAll() сохранения col. в файл
     */
    public boolean saveAll(List<T> itemsList) {
        col.clear();
        col.addAll(itemsList);
        boolean success = writeToFile();
        col.clear();
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
    public List<T> getAll() {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            col.clear();
            col.addAll((List<T>) ois.readObject());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return col; //вне зависимости от успешности считывания файла (есть он или нет) - коллекция col должна существовать ,хоть и пустая!
    }


    public List<T> getAllCollection () {
        return this.col;
    }

    /** получение коллекций (booking | flight) в зависимости от типа - T и fileName - название файла где лежат эти коллекции **/
    public List<T> getCollectionFromDB () throws IOException {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ){
            col = (List<T>) ois.readObject();
        }catch (FileNotFoundException | ClassNotFoundException error){
            error.printStackTrace(System.out);
        }

        return col;
    }

    /**
     * @param collectionObject и  - объект типа Flight или Booking
     * @return возвращает T - Flight или Booking
     **/

    public T saveCollectionToDB (T collectionObject) {
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

    public T getByIndex (int index){
        if (index >= 0 && index < col.size()){
            return this.col.get(index);
        }else return null;
    }

    public T getById(int id) {
        return null;
    }

}
