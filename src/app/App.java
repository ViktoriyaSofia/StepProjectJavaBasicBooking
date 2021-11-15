package app;

import app.domain.console.Console;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the best flight booking app!\n");
        Console console = new Console();
        console.run();
    }
}