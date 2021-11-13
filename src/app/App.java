package app;

import java.io.IOException;
import app.domain.console.Console;


public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the best flight booking app!\n");
        Console console = new Console();
        console.run();
    }
}