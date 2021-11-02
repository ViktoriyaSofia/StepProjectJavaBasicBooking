package com.booking.app.domain.console;

import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;

import java.io.*;
import java.util.*;

public class Console {
    private Map<Integer, String> mainMenuOfBookingApp;

    public Console() {
        mainMenuOfBookingApp = new HashMap<>();
        fillMainMenuOptions();
    }


    //  Метод fillMainMenuOptions() заполняет поле экземпляра класса Console Map<Integer, String> mainMenuOfBookingApp в конструкторе
    public void fillMainMenuOptions(){
        mainMenuOfBookingApp.put(1, "1  .....>>  View the online board flights from Kiev in the next 24 hours");
        mainMenuOfBookingApp.put(2, "2  .....>>  View flight information by flight ID");
        mainMenuOfBookingApp.put(3, "3  .....>>  Search for flights and Book the flights");
        mainMenuOfBookingApp.put(4, "4  .....>>  Cancel flight booking by it's ID");
        mainMenuOfBookingApp.put(5, "5  .....>>  View list of all bookings of a certain passenger");
        mainMenuOfBookingApp.put(6, "exit  ..>>  Quit the application");
    }


    //  Метод run() запускает работу экземпляра класса Console
    public void run(){
        while (true){
            showMainMenuOfBookingApp();
            try {
                implementTheSelectedActionOfMainMenu();
            } catch (WrongInputDataException e){
                WrongInputDataException.throwException();
            }
        }
    }


    //  Метод showMainMenuOfBookingApp() выводит главное меню приложения booking в консоль
    public void showMainMenuOfBookingApp(){
        System.out.println("\nSelect the option of the main menu:");
        for(Map.Entry<Integer, String> entry : mainMenuOfBookingApp.entrySet()){
            System.out.println(entry.getValue());
        }
    }


    //  implementTheSelectedActionOfMainMenu() - (реализует) вызывает методы для реализации выбранный пользователем пункт меню
    public void implementTheSelectedActionOfMainMenu() {
        System.out.print("Enter your choice [ 1 | 2 | 3 | 4 | 5 | exit ] >>> ");
        String userChoice = readInputData().trim();
        switch (userChoice){
            case "1" -> showOnlineFlightsScoreboard();
            case "2" -> showFlightInfoById();
            case "3" -> searchFlightAndBook();
            case "4" -> cancelBookingById();
            case "5" -> showAllBookingOfCertainPassenger();
            case "exit" -> exit();
            default -> throw new WrongInputDataException();
        }
    }


    //  Метод exit() завершает работу экземпляра класса Console
    private void exit() {
        System.exit(1);
    }


    //  Метод showOnlineFlightScoreboard() показывает информацию про все рейсы из Киева в ближайшие 24 часа (filtered DB FlightCollection)
    public void showOnlineFlightsScoreboard(){
        System.out.println("\nThe Online Scoreboard Flight from Kiev in the next 24 hours >>> ");
        System.out.println("Filtered DB FlightCollection!");
    }


    //  Метод showOnlineFlightScoreboard() показывает информацию по выбранному по ID из DB FlightCollection рейсу
    public void showFlightInfoById(){
        System.out.println("\nShows selected by ID Flight from DB FlightCollection!");
    }


    //  Метод searchFlightAndBook() предоставляет возможность выбрать определенные рейсы и забронтровать на определенный рейс билеты
    public void searchFlightAndBook(){
        System.out.println("\nSearch for flights and Book the flights!");
    }


    //  Метод cancelBookingById() отменяет (удаляет) бронь из DB BookingCollection по ID брони
    public void cancelBookingById(){
        System.out.println("\nCancel flight booking by it's ID!");
    }


    //  Метод showAllBookingOfCertainPassenger()
    public void showAllBookingOfCertainPassenger(){
        System.out.println("\nShows list of all bookings of a certain passenger!");
    }


    //  Метод readInputData() - считывает и возвращает введенные пользователем данные
    private String readInputData(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }


    //  checkInputDataInteger() - проверяет введенную строку на число
    public int checkInputDataInteger (String inputData, int min, int max){
        int inputDataInteger = -1;
        try {
            inputDataInteger = Integer.parseInt(inputData);
            if(inputDataInteger < min || inputDataInteger > max) {
                WrongInputDataException.throwException();
                inputDataInteger = -1;
            }
        } catch (NumberFormatException nfe){
            System.err.println("!ERROR! NumberFormatException! >> Enter integer only!");
        }
        return inputDataInteger;
    }

}