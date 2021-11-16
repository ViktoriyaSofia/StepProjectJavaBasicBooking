package app;

import app.domain.console.Console;


public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the best flight booking app!\n");
        Console console = new Console();
        console.run();
    }
}