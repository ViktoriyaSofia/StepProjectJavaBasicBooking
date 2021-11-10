package app.exceptions;

public class WrongInputDataException extends RuntimeException{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void throwException(){
        System.out.println(ANSI_RED + "\nWRONG INPUT DATA! >> " +
                "Look Available option or Required input data and Try again!"
                + ANSI_RESET);
    }
}