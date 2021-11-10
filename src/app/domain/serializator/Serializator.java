package app.domain.serializator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

//  Метод serialization() принимает на вход коллекцию List<T> и путь к файлу для звписи
//  - записывает (поверх данных в файле, если они есть) все объекты типа <T> из принятой коллекции в указанный файл
//  - если файла по данной директории не сцществует - создает его
    public <T> boolean serialization(List<T> collection, String path) {
        boolean isSerializationDone = false;
        File file = new File(path);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(collection);
            isSerializationDone = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSerializationDone;
    }


//  Метод deSerialization() принимает путь к файлу для чтения данных из него (как коллекцию ArrayList<T>)
//  - считывает и возврашат все объекты коллекции типа <T> из указанного файла
    public <T> ArrayList<T> deSerialization(String path) throws ClassNotFoundException {
        ArrayList<T> collection = null;
        File file = new File(path);

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            collection = (ArrayList<T>) ois.readObject();
            return collection;
        } catch (ClassNotFoundException | IOException e) {
            if(e.getClass().getName().equals("java.io.FileNotFoundException")){
                System.out.println(ANSI_RED + e.getClass().getName() + "! : " + e.getMessage() + ANSI_RESET);
            }
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}