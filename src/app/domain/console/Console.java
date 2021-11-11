package app.domain.console;

import app.dao.FlightDaoFile;
import app.domain.flight.Flight;
import app.services.FlightService;
import app.controllers.FlightController;

import app.domain.console.consoleController.ConsoleController;
import app.exceptions.WrongInputDataException;

import java.io.*;
import java.util.*;

public class Console {
    private final FlightController flightController;

    private final List<String> mainMenuOfBookingApp;
    private final ConsoleController consoleController;
    Scanner scanner = new Scanner(System.in);

    public Console() throws IOException {
        flightController = new FlightController(new FlightService(new FlightDaoFile()));
//        bookingController = new BookingController(new BookingService(new BookingDaoFile()));

        int sizeFlightCollection = flightController.getFlightsFromDB().size();
        System.out.println("\nFlight Collection contains " + sizeFlightCollection + " flights");

        if(sizeFlightCollection == 0){
            System.out.println(">>> Generating Flight Collection!");
            flightController.generateFlightDB(500, 30);
            System.out.println("New Flight Collection contains " + flightController.getAllFlights().size() + " flights");
        }

//        List<Booking> bookingCollection = bookingController.getBookingFromDB();
//        System.out.println("Booking Collection contains " + bookingCollection.size() + " bookings");

        mainMenuOfBookingApp = new ArrayList<>();
        consoleController = new ConsoleController();
        fillMainMenuOptions();
    }


    //  Метод fillMainMenuOptions() - заполняет поле экземпляра класса Console List<String> mainMenuOfBookingApp в конструкторе
    private void fillMainMenuOptions(){
        mainMenuOfBookingApp.add("0  .....>>  View all flights from Kiev");
        mainMenuOfBookingApp.add("1  .....>>  View the flights from Kiev in the next 24 hours");
        mainMenuOfBookingApp.add("2  .....>>  View flight information by flight ID");
        mainMenuOfBookingApp.add("3  .....>>  Search for flights and Book the flights");
        mainMenuOfBookingApp.add("4  .....>>  Cancel flight booking by it's ID");
        mainMenuOfBookingApp.add("5  .....>>  View list of all bookings of certain passenger");
        mainMenuOfBookingApp.add("exit  ..>>  Quit the application");
    }


    //  Метод run() - запускает работу экземпляра класса Console
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


    //  Метод showMainMenuOfBookingApp() - выводит главное меню приложения booking в консоль
    private void showMainMenuOfBookingApp(){
        System.out.println("\nSelect the option of the main menu:");
        mainMenuOfBookingApp.forEach(System.out::println);
    }


    //  implementTheSelectedActionOfMainMenu() - (реализует) вызывает методы для реализации выбранного пользователем пункта меню
    private void implementTheSelectedActionOfMainMenu() {
        System.out.print("Enter your choice, available option:[ 0 | 1 | 2 | 3 | 4 | 5 | exit ] >>> ");
        String userChoice = scanner.nextLine().toLowerCase().trim();
        switch (userChoice){
            case "0" -> showAllFlightsCollection();
            case "1" -> showOnlineFlightsScoreboard();
            case "2" -> showFlightInfoById();
            case "3" -> searchFlightAndBook();
            case "4" -> cancelBookingById();
            case "5" -> showAllBookingsOfCertainPassenger();
            case "exit" -> exit();
            default -> throw new WrongInputDataException();
        }
    }


    //  Метод exit() завершает работу экземпляра класса Console
    private void exit() {
        scanner.close();
        System.out.println("\nExit the application. Goodbye!");
        System.exit(1);
    }


    //  Метод showAllFlightsCollection() - показывает информацию про все рейсы из List<Flight> flightCollection
    private void showAllFlightsCollection() {
        System.out.println("\nView all flights from Kiev >>> ");
        System.out.println("\nAll Flight from Kiev in flightCollection DB:");
        flightController.showFlightsCollection(flightController.sortFlightsByDate(flightController.getAllFlights()));
    }


    //  Метод showOnlineFlightScoreboard() показывает информацию про все рейсы из Киева в ближайшие 24 часа (filtered DB FlightCollection)
    private void showOnlineFlightsScoreboard(){
        System.out.println("\n>>> View the flights from Kiev in the next 24 hours");
        System.out.println("\nThe Online Scoreboard Flight:");
        flightController.showFlightsCollection(flightController.getFlightsInOneDayPeriod());
    }


    //  Метод showFlightInfoById() - показывает информацию по выбранному по ID(flightID) из DB FlightCollection рейсу
    private void showFlightInfoById(){
        System.out.println("\n>>> View flight information by flight ID");
        int flightID = -1;

        while (flightID == -1){
            System.out.print("Enter flight ID of Flight you'd like to get information about, required: [integer only [1000:9999]] >>> ");
            flightID = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 1000, 9999);
        }

        Flight flight = flightController.getFlightById(flightID);
        if(flight != null){
            System.out.println("\nFound Flight with flight ID='" + flightID + "'. Information about:");
            flight.prettyFormatFlightFullInfo();
        } else {
            System.out.println("\nThere is No Flight Found with flight ID='" + flightID + "'");
        }
    }


    //  Метод searchFlightAndBook() - предоставляет возможность выбрать определенные рейсы и забронтровать на определенный рейс билеты
    //  если добро на бронь - добавляет booking(бронь) в BookingCollection, - апдейтит FlightCollection по рейсу (SoldPlaces, AvailablePlaces)
    private void searchFlightAndBook(){
        System.out.println("\n>>> Search and Book the flights");
        String destinationStr = "", dateStr = "";
        int ticketsNumber = -1;

        System.out.println("To search for flights, please enter some data:");
        while (destinationStr.equals("")){
            System.out.print("Enter flight arrival destination, required: [characters only] >>> ");
            destinationStr = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        destinationStr = consoleController.toUpperCaseFirstLetterEachWorld(destinationStr);

        while (dateStr.equals("")){
            System.out.print("Enter the departure date of flights, required: [dd/MM/yyyy] >>> ");
            dateStr = consoleController.checkInputDataDateString(scanner.nextLine().toLowerCase().trim());
        }

        while (ticketsNumber == -1){
            System.out.print("Enter number of tickets you'd like to book, required: [integer only, 1 to 5 tickets available in one booking] >>> ");
            ticketsNumber = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 1, 5);
        }

        List<Flight> foundFlights = flightController.findFlights(destinationStr, dateStr, ticketsNumber);
        if(foundFlights.size() > 0){
            System.out.printf("\nFound Flights to %s, on date %s, with the required number (%d) of available tickets: \n",
                    destinationStr, dateStr, ticketsNumber);
            flightController.showFlightsCollection(foundFlights);
            System.out.println();

            int listSize = foundFlights.size();
            int userChoice = -1;
            while (userChoice == -1){
                System.out.print("Enter 0 to go back to the main menu, OR " +
                        "\nEnter the number of flight from shown up list of Found Flights (NOT ID!) to continue booking, required: [1:" + listSize  + "] >>> ");
                userChoice = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 0, listSize);
                if(userChoice == 0) {
                    System.out.println("Exit booking!");
                    break;
                }
            }

            if(userChoice > 0){
                Flight flight = foundFlights.get(userChoice-1);

//                PassengerCollection passengersList = createPassengerList(ticketsNumber);
//                Booking booking = new Booking(flight, passengersList);

//                int bookingID = controllerBookingCollection.updateBookingCollection(booking);
//                if(bookingID != -1){
//                    controllerBookingCollection.saveBookingCollectionToDB(controllerBookingCollection.getBookingCollection());
//
//                    flight.setSoldPlaces(flight.getSoldPlaces() + ticketsNumber);
//                    flight.setAvailablePlaces();
//                    controllerFlightCollection.updateFlightCollection(flight);
//                    controllerFlightCollection.saveFlightCollectionToDB(controllerFlightCollection.getFlightCollection());
//
//                    System.out.println("\nBooking Done!");
//                    booking.prettyFormatBookingFullInfo();
//                } else {
//                    System.out.println("\nBooking Fail! Try again!");
//                }
            }
        } else { System.out.printf("\nFlights by your request Not Found!" +
                        "\nThere is no flights to %s, on date %s, with the required number (%d) of available tickets.%n"
                , destinationStr, dateStr, ticketsNumber); }

    }


    //  Метод createPassengerList() - формирует коллекцию пассажиров для брони билетов на рейс
//    private PassengerCollection createPassengerList(int ticketsNumber){
//        System.out.println("\n>>> Continue booking!");
//        PassengerCollection passengersList = new PassengerCollection();
//        for(int i = 1; i <= ticketsNumber; i++){
//            System.out.println();
//            String surName = "", name = "";
//
//            while (name.equals("")){
//                System.out.printf("Enter the Name of passenger %d, required: [characters only] >>> ", i);
//                name = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
//            }
//            name = consoleController.toUpperCaseFirstLetterEachWorld(name);
//
//            while (surName.equals("")){
//                System.out.printf("Enter the Last Name of passenger %d, required: [characters only] >>> ", i);
//                surName = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
//            }
//            surName = consoleController.toUpperCaseFirstLetterEachWorld(surName);
//
//            Passenger passenger = new Passenger(name, surName);
//            passengersList.addPassenger(passenger);
//        }
//        return passengersList;
//    }


    //  Метод cancelBookingById() - отменяет (удаляет) бронь из DB BookingCollection по ID брони
    //  - апдейтит FlightCollection по рейсу (SoldPlaces, AvailablePlaces)
    private void cancelBookingById(){
        System.out.println("\n>>> Cancel flight booking by it's ID!");
        int bookingID = -1;

        while (bookingID == -1){
            System.out.print("Enter booking ID of Booking you'd like to cancel, required: [integer only [1000:9999]] >>> ");
            bookingID = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 1000, 9999);
        }

//        Booking booking = controllerBookingCollection.getBookingByBookingID(bookingID);
//        if(booking != null){
//            int seatsInBooking = booking.getNumberOfSeats();
//            Flight flight = booking.getFlight();
//
//            if(controllerBookingCollection.deleteBookingByID(bookingID)){
//                flight.setSoldPlaces(flight.getSoldPlaces() - seatsInBooking);
//                flight.setAvailablePlaces();
//
//                controllerFlightCollection.updateFlightCollection(flight);
//                controllerFlightCollection.saveFlightCollectionToDB(controllerFlightCollection.getFlightCollection());
//
//                controllerBookingCollection.saveBookingCollectionToDB(controllerBookingCollection.getBookingCollection());
//                System.out.println("\nThe Booking with booking ID='" + bookingID + "' was canceled!");
//            }
//
//        } else {
//            System.out.println("\nThere is No Booking Found with booking ID='" + bookingID + "'");
//        }
    }


    //  Метод showAllBookingOfCertainPassenger() - находит (по имени и фамилии пассажира) его брони и выводит их в консоль
    private void showAllBookingsOfCertainPassenger(){
        System.out.println("\n>>> View list of all bookings of certain passenger");
        String surName = "", name = "";

        while (name.equals("")){
            System.out.printf("Enter the Name of passenger, required: [characters only] >>> ");
            name = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        name = consoleController.toUpperCaseFirstLetterEachWorld(name);

        while (surName.equals("")){
            System.out.printf("Enter the Last Name of passenger, required: [characters only] >>> ");
            surName = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        surName = consoleController.toUpperCaseFirstLetterEachWorld(surName);

//        List<Booking> bookings = controllerBookingCollection.getBookingListOfCertainPassenger(name, surName);
//
//        if(bookings.size() != 0){
//            int i = 1;
//            System.out.printf("\nBookings for passenger %s %s:%n", name, surName);
//            for(Booking booking : bookings){
//                System.out.println("#" + i + "  >>> ");
//                booking.prettyFormatBookingFullInfo();
//                i++;
//            }
//        } else {
//            System.out.printf("\nThere is No Bookings for passenger %s %s!%n", name, surName);
//        }
    }

}