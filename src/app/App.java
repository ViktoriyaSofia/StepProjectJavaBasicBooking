package app;

import app.domain.console.Console;
import app.exceptions.WrongInputDataException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
        Console console = new Console();
        console.run();
    }
}