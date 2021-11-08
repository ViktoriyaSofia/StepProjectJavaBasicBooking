package app.domain.console.wrongInputDataException;

public class WrongInputDataException extends RuntimeException{
    public static void throwException(){
        System.err.println("WRONG INPUT DATA! >> " +
                "Look required input option of menu or required input data rules and try again!\n");
    }
}