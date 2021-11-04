package com.booking.app.domain.console.consoleController;

import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConsoleController {

//  Метод checkInputDataChars() - проверяет введенные данные на только символы и удаляет все лишние пробелы
    public String checkInputDataChars(String inputData){
        if(!inputData.matches("[\\p{L}| ]+")){
            System.err.println("\nWRONG INPUT! >> Input data should content characters only!");
            return "";
        }
        return inputData.trim().replaceAll("\\s+", " ");
    }


//  Метод toUpperCaseFirstLetterEachWorld() - задает первую букву каждого слова заглавной
    public  String toUpperCaseFirstLetterEachWorld(String text) {
        StringBuilder builder = new StringBuilder(text);
        //выставляем первый символ заглавным, если это буква
        //  if (Character.isAlphabetic(text.codePointAt(0)))
            builder.setCharAt(0, Character.toUpperCase(text.charAt(0)));

        //в цикле меняем буквы, перед которыми пробел на заглавные
        for (int i = 1; i < text.length(); i++)
            if (Character.isSpaceChar(text.charAt(i - 1)))
                builder.setCharAt(i, Character.toUpperCase(text.charAt(i)));

        return builder.toString();
    }


//  Метод checkInputDataDateString() - проверяет введенные данные на соответствие нужного строчного формата даты
    public String checkInputDataDateString(String dateStr) {
        try {
            long flightDateMilli = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dateNow = LocalDate.now().format(formatter);
//            System.out.println(dateNow);

            if( (Instant.ofEpochMilli(flightDateMilli).atZone(ZoneId.systemDefault()).toLocalDate())
                    .isBefore(LocalDate.now()) ){
                System.err.println("\nWRONG INPUT! >> There is no flight before today available!");
                return dateStr = "";
            }
        } catch (DateTimeParseException e) {
            System.err.println("\nERROR! >> " + e.getLocalizedMessage()
                    + "\nWRONG INPUT! >> Check day/month/year you enter correct!");
            return dateStr = "";
        }
        return dateStr;
    }


//  Метод checkInputDataInteger() - проверяет введенную строку на число
    public int checkInputDataInteger (String inputData, int min, int max){
        int inputDataInteger = -1;
        try {
            inputDataInteger = Integer.parseInt(inputData);
            if(inputDataInteger < min || inputDataInteger > max) {
                WrongInputDataException.throwException();
                inputDataInteger = -1;
            }
        } catch (NumberFormatException nfe){
            System.err.println("\nERROR! NumberFormatException! >> Enter integer only!");
        }
        return inputDataInteger;
    }

}