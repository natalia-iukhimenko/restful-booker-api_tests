import com.github.javafaker.Faker;
import dto.BookingDTO;
import dto.BookingDatesDTO;

public class TestDataGenerator {
    public static BookingDTO getValidBooking() {
        Faker faker = new Faker();
        BookingDTO booking = new BookingDTO();
        booking.setFirstName(faker.name().firstName());
        booking.setLastName(faker.name().lastName());
        booking.setAdditionalNeeds("No needs");
        booking.setBookingDates(getTestBookingDates());
        booking.setDepositPaid(true);
        booking.setTotalPrice(100);
        return booking;
    }

    public static BookingDatesDTO getTestBookingDates() {
        BookingDatesDTO bookingDates = new BookingDatesDTO();
        bookingDates.setCheckin("2020-10-10"); // current date + 5
        bookingDates.setCheckOut("2020-10-18"); // current date + 10
        return bookingDates;
    }
}
