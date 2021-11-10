package app;

import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.domain.console.Console;
import app.domain.console.wrongInputDataException.WrongInputDataException;
import app.services.BookingService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, WrongInputDataException, ClassNotFoundException {
        System.out.println("Welcome to the best flight booking app!");
        Console console = new Console();
//        console.run();

        //инстанциируем сервисы: dao, service, controller для booking:
        BookingDaoFile bookingDao = new BookingDaoFile();
        BookingService bs = new BookingService(bookingDao);
        BookingController bc = new BookingController(bs);

        /**
         * (после финального мерджа проекта, блок кода стр.23 - 30 удалим:
         * (удалим запуск методов bc.bookingInit(); bc.bookingMethodsDemo();)
         */
        //создаём тестовые 2шт резервирования с flightID 15 и 27, по 4 пассажира в каждом
        bc.bookingInit();
        //выводим на экран резервирование для пассажира:
        // и удаляем резервирование заданного ID- номера:
        bc.bookingMethodsDemo();
    }
}