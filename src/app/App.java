package app;

<<<<<<< HEAD
=======
import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.domain.booking.Booking;
import app.domain.console.Console;
import app.exceptions.WrongInputDataException;
import app.services.BookingService;
>>>>>>> dev
import java.io.IOException;
import app.domain.console.Console;


public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the best flight booking app!\n");
        Console console = new Console();
        console.run();

        /**
         * НАЧАЛО КОДА ВЛАДА:
         */
        //инстанциируем сервисы: dao, service, controller для booking:
//        BookingDaoFile bookingDao = new BookingDaoFile();
//        BookingService bs = new BookingService(bookingDao);
//        BookingController bc = new BookingController(bs);


        //создаём тестовые 2шт резервирования с flightID 15 и 27, по 4 пассажира в каждом
//        bc.bookingInit();


        /**
         * ниже запускаются все демонстрационные методы (после финального мерджа проекта, всё это удалим:)
         */


        // удаляем резервирование заданного ID- номера:
//        String IDofBookingToBeDeleted = bs.dao.retrieve().get(0).bookingID;
//        bc.deleteBookingById(IDofBookingToBeDeleted);


        //выводим на экран резервирование для пассажира:
//        bc.printBookingOfPineloppa("Pineloppa", "Zdurovskaya");

        // вывести на экран все имеющиеся в базе резервирования:
//        bc.printAllBookings();


    }
}