package com.booking.app.domain.dateMethods;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class DateMethods {

//  Метод generateLocalDateTime() генерирует и возвращает long epochSecondOfDay
//  в период [от текущей LocalDateTime.now() до LocalDateTime.now() + принятое кол-во дней]
    public long generateLocalDateTime(int days){
        LocalDateTime date = LocalDateTime.now();
        long minDay = date.toEpochSecond(ZoneOffset.UTC);
        long maxDay = date.plusDays(days).toEpochSecond(ZoneOffset.UTC);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return randomDay;
    }


//  Метод convertEpochSecondOfDayToLocalDateTime() конвертирует long epochSecondOfDay в LocalDateTime date
//    - возвращает LocalDateTime date в стандартном виде LocalDateTime (например: 2022-01-08T01:23:33.123456)
    public LocalDateTime convertEpochSecondOfDayToLocalDateTime(long epochSecondOfDay){
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecondOfDay, 0, ZoneOffset.UTC);
        return localDateTime;
    }


//  Метод formatLocalDateTimeToStringDateTime() конвертирует LocalDateTime date в строку формата "dd-MM-yyyy hh:mm:ss a"
//    - возвращает String dateStr в формате "dd/MM/yyyy hh:mm:ss a" (например: 08/01/2022 01:23:33 AM)
    public String formatLocalDateTimeToStringDateTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"));
    }


//  Метод formatLocalDateTimeToStringDate() конвертирует LocalDateTime date в строку ДАТА формата "dd/MM/yyyy" (04/11/2021)
    public String formatLocalDateTimeToStringDate(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


//  Метод formatLocalDateTimeToStringTime() конвертирует LocalDateTime date в строку ВРЕМЯ формата "hh:mm:ss a" (03:49:59 PM)
    public String formatLocalDateTimeToStringTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }


//  Метод getLocalDateTimeFromDateAndTimeStrings()
//    - принимает строку дата формата "dd/MM/yyyy" ("04/11/2021")
//    - принимает строку время формата "hh:mm:ss a" ("03:16:57 PM") или ("03:16:57 AM")
//    - возвращает LocalDateTime date в стандартном виде LocalDateTime (например: 2022-01-08T01:23:33 (без наносекунд))
    public LocalDateTime getLocalDateTimeFromDateAndTimeStrings(String date, String time) {
        String dataStringAndTimeString = date + " " + time;
        LocalDateTime localDateTime = LocalDateTime.parse(dataStringAndTimeString, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"));
        return localDateTime;
    }


//  Метод getLocalDateTimeFromStringDateTime()
//    - принимает строку датаВремя формата "dd/MM/yyyy hh:mm:ss a" ("например: 04/11/2021 03:16:57 PM")
//    - возвращает LocalDateTime date в стандартном виде LocalDateTime (например: 2022-01-08T01:23:33 (без наносекунд))
    public LocalDateTime getLocalDateTimeFromStringDateTime(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"));
        return localDateTime;
    }


//  Метод convertLocalDateTimeToEpochSecond() конвертирует LocalDateTime date в long epochSecondOfDay
    public long convertLocalDateTimeToEpochSecond(LocalDateTime localDateTime){
        long epochSecondOfDay = localDateTime.toEpochSecond(ZoneOffset.UTC);
        return epochSecondOfDay;
    }

}