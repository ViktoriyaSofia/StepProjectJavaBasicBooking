package com.booking.app.domain.console;

import com.booking.app.domain.console.consoleController.ConsoleController;
import com.booking.app.domain.console.wrongInputDataException.WrongInputDataException;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Console {
    private Map<Integer, String> mainMenuOfBookingApp;
    private ConsoleController consoleController;

    public Console() {
        mainMenuOfBookingApp = new HashMap<>();
        consoleController = new ConsoleController();
        fillMainMenuOptions();
    }


    //  Метод fillMainMenuOptions() заполняет поле экземпляра класса Console Map<Integer, String> mainMenuOfBookingApp в конструкторе
    private void fillMainMenuOptions(){
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
    private void showMainMenuOfBookingApp(){
        System.out.println("\nSelect the option of the main menu:");
        for(Map.Entry<Integer, String> entry : mainMenuOfBookingApp.entrySet()){
            System.out.println(entry.getValue());
        }
    }


    //  implementTheSelectedActionOfMainMenu() - (реализует) вызывает методы для реализации выбранного пользователем пункта меню
    private void implementTheSelectedActionOfMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice [ 1 | 2 | 3 | 4 | 5 | exit ] >>> ");
        String userChoice = scanner.nextLine().toLowerCase().trim();
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
    private void showOnlineFlightsScoreboard(){
        System.out.println("\nThe Online Scoreboard Flight from Kiev in the next 24 hours >>> ");
        System.out.println("Filtered DB FlightCollection!");
    }


    //  Метод showFlightInfoById() показывает информацию по выбранному по ID из DB FlightCollection рейсу
    private void showFlightInfoById(){
        System.out.println("\nShows selected by ID Flight from DB FlightCollection!");
    }


    //  Метод searchFlightAndBook() предоставляет возможность выбрать определенные рейсы и забронтровать на определенный рейс билеты
    private void searchFlightAndBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n>>> Search and Book the flights! ");
        String destinationStr = "", dateStr = "";
        int ticketsNumber = -1;

        System.out.println("To search for flights, please enter some data: ");
        while (destinationStr.equals("")){
            System.out.print("Enter flight destination [characters only] >>> ");
            destinationStr = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        destinationStr = consoleController.toUpperCaseFirstLetterEachWorld(destinationStr);

        while (dateStr.equals("")){
            System.out.print("Enter the date of flights [dd/MM/yyyy] >>> ");
            dateStr = consoleController.checkInputDataDateString(scanner.nextLine().toLowerCase().trim());
        }

        while (ticketsNumber == -1){
            System.out.print("Enter number of tickets you'd like to book [integer only, 1 to 5 tickets available in one booking] >>> ");
            ticketsNumber = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 1, 5);
        }

        System.out.printf("\nFound flights to %s, on date %s, with the required number (%d) of available tickets: \n",
                destinationStr, dateStr, ticketsNumber);
        //  List<Flight> foundFlights = new ArrayList<>();

//  пока нет базы данных, например нашлось 4 рейса:
        int sizeOfFoundFlights = 4;
        int userChoice = -1;
        while (userChoice == -1){
            System.out.print("\nEnter 0 to go back to the main menu, or " +
                    "\nChoose the sequence number of flight to continue booking [1:4} >>> ");
            userChoice = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 0, 4);
            if(userChoice == 0) break;
        }

        if(userChoice > 0){
            List<HashMap> passengersList = new ArrayList<>();
            passengersList = createPassengerList(ticketsNumber);

            System.out.println("\nBooking done for passengers:");
            passengersList.forEach(p -> {
                //  for(Object entry  : p.entrySet()){ System.out.print(entry.toString() + ", ");}
                for(Object key : p.keySet()){
                    System.out.print(key + ": " + p.get(key) + ", ");
                }
                System.out.println();
            });
        }
    }


    //  Метод createPassengerList() формирует соллекцию пассажиров для брони билетов на рейс
    private List<HashMap> createPassengerList(int ticketsNumber){
        Scanner scanner = new Scanner(System.in);
        List<HashMap> passengersList = new ArrayList<>();
        for(int i = 1; i <= ticketsNumber; i++){
            String surName = "", name = "";

            while (surName.equals("")){
                System.out.printf("Enter the Last name of passenger %d [characters only] >>> ", i);
                surName = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
            }
            surName = consoleController.toUpperCaseFirstLetterEachWorld(surName);

            while (name.equals("")){
                System.out.printf("Enter the Name of passenger %d [characters only] >>> ", i);
                name = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
            }
            name = consoleController.toUpperCaseFirstLetterEachWorld(name);

            HashMap<String, String> passenger = new HashMap<>();
            passenger.put("surname", surName);
            passenger.put("name", name);
            passengersList.add(passenger);
        }
        return passengersList;
    }


    //  Метод cancelBookingById() отменяет (удаляет) бронь из DB BookingCollection по ID брони
    private void cancelBookingById(){
        System.out.println("\nCancel flight booking by it's ID!");
    }


    //  Метод showAllBookingOfCertainPassenger()
    private void showAllBookingOfCertainPassenger(){
        System.out.println("\nShows list of all bookings of a certain passenger!");
    }

}