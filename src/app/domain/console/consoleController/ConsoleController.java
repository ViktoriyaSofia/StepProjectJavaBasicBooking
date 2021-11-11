package app.domain.console.consoleController;

import app.domain.console.wrongInputDataException.WrongInputDataException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConsoleController {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    //  Метод checkInputDataChars() - проверяет введенные данные на только символы и удаляет все лишние пробелы
    public String checkInputDataChars(String inputData){
        if(!inputData.matches("[\\p{L}| ]+")){
            System.out.println(ANSI_RED + "\nWRONG INPUT! >> Input data should content characters only!\n" + ANSI_RESET);
            return "";
        }
        return inputData.trim().replaceAll("\\s+", " ");
    }


    //  Метод toUpperCaseFirstLetterEachWorld() - задает первую букву каждого слова заглавной
    public  String toUpperCaseFirstLetterEachWorld(String text) {
        StringBuilder builder = new StringBuilder(text);
        builder.setCharAt(0, Character.toUpperCase(text.charAt(0)));
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

            if( (Instant.ofEpochMilli(flightDateMilli).atZone(ZoneId.systemDefault()).toLocalDate())
                    .isBefore(LocalDate.now()) ){
                System.out.println(ANSI_RED + "\nWRONG INPUT! >> There is no flight before today available!\n" + ANSI_RESET);
                return dateStr = "";
            }
        } catch (DateTimeParseException e) {
            System.out.println(ANSI_RED + "\nERROR! >> " + e.getLocalizedMessage()
                    + "\nWRONG INPUT! >> Check day/month/year you enter correct!\n" + ANSI_RESET);
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
                inputDataInteger = -1;
                WrongInputDataException.throwException();
                System.out.println();
            }
        } catch (NumberFormatException nfe){
            System.out.println(ANSI_RED + "\nERROR! NumberFormatException! >> Enter integer only!\n" + ANSI_RESET);
            inputDataInteger = -1;
        }
        return inputDataInteger;
    }
}