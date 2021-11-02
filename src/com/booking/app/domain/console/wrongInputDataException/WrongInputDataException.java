package com.booking.app.domain.console.wrongInputDataException;

public class WrongInputDataException extends RuntimeException{
    public Throwable throwException() throws RuntimeException {
        throw new RuntimeException("!WRONG INPUT! >> " +
                "Look required input option of menu or data input rules and try again!");
    }
}
