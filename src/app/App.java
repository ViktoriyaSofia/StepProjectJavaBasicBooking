package app;

import app.controllers.BookingController;
import app.dao.BookingDaoFile;
import app.services.BookingService;

public class App {
    public static void main(String[] args) {

        /**
         * НАЧАЛО КОДА ВЛАДА:
         */
        //инстанциируем сервисы: dao, service, controller для booking:
        BookingDaoFile bookingDao = new BookingDaoFile();
        BookingService bs = new BookingService(bookingDao);
        BookingController bc = new BookingController(bs);


        //создаём тестовые 2шт резервирования с flightID 15 и 27, по 4 пассажира в каждом
        bc.bookingInit();


        /**
         * ниже запускаются все демонстрационные методы (после финального мерджа проекта, всё это удалим:)
         */


        // удаляем резервирование заданного ID- номера:
        String IDofBookingToBeDeleted = bs.dao.retrieve().get(0).bookingID;
        int cancelledFlightID = bc.deleteBookingById(IDofBookingToBeDeleted);
        System.out.println("flightID of cancelled booking: " + cancelledFlightID);

        //выводим на экран резервирование для пассажира:
//        bc.printBookingOfPineloppa("Pineloppa", "Zdurovskaya");

        // вывести на экран все имеющиеся в базе резервирования:
//        bc.printAllBookings();


    }

}