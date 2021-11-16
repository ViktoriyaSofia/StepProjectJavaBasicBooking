package app.domain.console;

import app.controllers.BookingController;
import app.controllers.FlightController;
import app.dao.BookingDaoFile;
import app.dao.FlightDaoFile;
import app.domain.booking.Booking;
import app.domain.booking.Passenger;
import app.domain.flight.Flight;
import app.exceptions.WrongInputDataException;
import app.services.BookingService;
import app.services.FlightService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final FlightController flightController;
    private final BookingController bookingController;

    private final List<String> mainMenuOfBookingApp;
    private final ConsoleController consoleController;
    Scanner scanner = new Scanner(System.in);

    public Console() {
        flightController = new FlightController(new FlightService(new FlightDaoFile("flights.bin")));
        bookingController = new BookingController(new BookingService(new BookingDaoFile()));

        int sizeFlightCollection = flightController.getFlightsFromDB().size();
        System.out.println("Flight Collection contains " + sizeFlightCollection + " flights\n");

        System.out.println("Booking Collection contains " + bookingController.retrieveAllBookings().size() + " bookings");

        if(sizeFlightCollection == 0){
            System.out.println("\n>>> Generating Flight Collection!");
            flightController.generateFlightDB(1000, 15);
            System.out.println("New Flight Collection contains " + flightController.getFlightsFromDB().size() + " flights");
        }

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
        mainMenuOfBookingApp.add("4  .....>>  Cancel booking by it's ID");
        mainMenuOfBookingApp.add("5  .....>>  View bookings of certain passenger");
        mainMenuOfBookingApp.add("6  .....>>  View list of all bookings");
        mainMenuOfBookingApp.add("exit  ..>>  Quit the application");
    }


    //  Метод run() - запускает работу экземпляра класса Console
    public void run(){
        while (true){
            showMainMenuOfBookingApp();
            try {
                implementTheSelectedActionOfMainMenu();
            } catch (WrongInputDataException | IOException e){
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
    private void implementTheSelectedActionOfMainMenu() throws IOException {
        System.out.print("Enter your choice, available option: [ 0 | 1 | 2 | 3 | 4 | 5 | 6 | exit ] >>> ");
        String userChoice = scanner.nextLine().toLowerCase().trim();
        switch (userChoice){
            case "0" -> showAllFlightsCollection();
            case "1" -> showOnlineFlightsScoreboard();
            case "2" -> showFlightInfoById();
            case "3" -> searchFlightAndBook();
            case "4" -> cancelBookingById();
            case "5" -> showAllBookingsOfCertainPassenger();
            case "6" -> showAllBookingsCollection();
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
        flightController.showFlightsCollection(flightController.sortFlightsByDate(flightController.getFlightsFromDB()));
    }


    //  Метод showOnlineFlightScoreboard() показывает информацию про все рейсы из Киева в ближайшие 24 часа (filtered DB FlightCollection)
    private void showOnlineFlightsScoreboard(){
        System.out.println("\n>>> View the flights from Kiev in the next 24 hours");
        System.out.println("\nThe Online Scoreboard Flight:");
        flightController.showFlightsCollection(flightController.sortFlightsByDestination(flightController.getFlightsInOneDayPeriod()));
    }


    //  Метод showFlightInfoById() - показывает информацию по выбранному по ID(flightID) из DB FlightCollection рейсу
    private void showFlightInfoById(){
        System.out.println("\n>>> View flight information by flight ID");
        int flightID = -1;

        while (flightID == -1){
            System.out.print("Enter flight ID of Flight you'd like to get information about, required: [integer only [100000 : 999999]] >>> ");
            flightID = consoleController.checkInputDataInteger(scanner.nextLine().toLowerCase().trim(), 100000, 999999);
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
            foundFlights = flightController.sortFlightsByDate(foundFlights);
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
                int flightID = foundFlights.get(userChoice-1).getFlightID();

                List<Passenger> passengersList = createPassengerList(ticketsNumber);

                Booking booking = bookingController.createBooking(flightID, passengersList);

                if(booking != null){
                    Flight flight = flightController.getFlightById(flightID);
                    flight.setSoldPlaces(flight.getSoldPlaces() + ticketsNumber);
                    flight.setAvailablePlaces();

                    flightController.updateFlight(flight);

                    System.out.println("\nBooking Done!");
                    System.out.println(booking);
                } else {
                    System.out.println("\nBooking Fail! Try again!");
                }
            }
        } else { System.out.printf("%nFlights by your request Not Found!" +
                        "%nThere is no flights to %s, on date %s, with the required number (%d) of available tickets.%n"
                , destinationStr, dateStr, ticketsNumber); }

    }


    //  Метод createPassengerList() - формирует коллекцию пассажиров для брони билетов на рейс
    private List<Passenger> createPassengerList(int ticketsNumber){
        System.out.print("\n>>> Continue booking!");
        List<Passenger> passengersList = new ArrayList<>();
        for(int i = 1; i <= ticketsNumber; i++){
            System.out.println();
            String surName = "", name = "";

            while (name.equals("")){
                System.out.printf("Enter the Name of passenger %d, required: [characters only] >>> ", i);
                name = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
            }
            name = consoleController.toUpperCaseFirstLetterEachWorld(name);

            while (surName.equals("")){
                System.out.printf("Enter the Last Name of passenger %d, required: [characters only] >>> ", i);
                surName = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
            }
            surName = consoleController.toUpperCaseFirstLetterEachWorld(surName);

            Passenger passenger = new Passenger(name, surName);
            passengersList.add(passenger);
        }
        return passengersList;
    }


    //  Метод cancelBookingById() - отменяет (удаляет) бронь из DB BookingCollection по ID брони
    //  - апдейтит FlightCollection по рейсу (SoldPlaces, AvailablePlaces)
    private void cancelBookingById(){
        System.out.println("\n>>> Cancel flight booking by it's ID!");
        String bookingID = "";
        System.out.print("Enter booking ID of Booking you'd like to cancel, example: [ef77909a5fcd4ffab52de499d3f8b198] >>> ");
        bookingID = scanner.nextLine().toLowerCase().trim();
        System.out.println();

        Booking booking = bookingController.getBookingById(bookingID);
        if(booking != null){
            int flightIDToUpdate = booking.getFlightID();
            Flight flightToUpdate = flightController.getFlightById(flightIDToUpdate);

            if(flightToUpdate != null){
                int seatsToCancel = booking.getpL().size();

                int isBookingCanceled = bookingController.deleteBookingById(bookingID);

                if(isBookingCanceled != -1){
                    System.out.println("\nThe Booking with booking ID='" + bookingID + "' was canceled!");

                    flightToUpdate.setSoldPlaces(flightToUpdate.getSoldPlaces() - seatsToCancel);
                    flightToUpdate.setAvailablePlaces();

                    flightController.updateFlight(flightToUpdate);
                }
            }

        } else {
            System.out.println("There is No Booking Found with booking ID='" + bookingID + "'");
        }

    }


    //  Метод showAllBookingOfCertainPassenger() - находит (по имени и фамилии пассажира) его брони и выводит их в консоль
    private void showAllBookingsOfCertainPassenger(){
        System.out.println("\n>>> View bookings of certain passenger");
        String surName = "", name = "";

        while (name.equals("")){
            System.out.print("Enter the Name of passenger, required: [characters only] >>> ");
            name = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        name = consoleController.toUpperCaseFirstLetterEachWorld(name);

        while (surName.equals("")){
            System.out.print("Enter the Last Name of passenger, required: [characters only] >>> ");
            surName = consoleController.checkInputDataChars(scanner.nextLine().toLowerCase());
        }
        surName = consoleController.toUpperCaseFirstLetterEachWorld(surName);
        System.out.println();
        bookingController.printBookingOfGivenPassenger(name, surName);
    }


    //  Метод showAllBookingsCollection() - показывает информацию про все брони
    public void showAllBookingsCollection(){
        System.out.println("\nView list of all bookings >>> \n");
        bookingController.printAllBookings();
    }

}