package app.domain.serializator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {

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
    public <T> ArrayList<T> deSerialization(String path) throws InvalidObjectException {
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
            e.printStackTrace();
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

        throw new InvalidObjectException("Object fail");
    }


//  Метод serializationAppending() принимает на вход коллекцию List<T> и путь к файлу для звписи:
//  предполагается, что в файле уже есть идентичная коллекция объектов
//  и нужно к ней (append) дозаписать другую коллекцию такого же типа
//  - дозаписывает в конец указанного файла все объекты типа <T> из принятой коллекции
    public <T> boolean serializationAppending(List<T> collection, String path) {
        boolean isSerializationDone = false;
        File file = new File(path);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            if (file.exists()) {
                ArrayList<T> newData = deSerialization(path);
                newData.addAll(collection);
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(newData);
                isSerializationDone = true;
            }
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

}