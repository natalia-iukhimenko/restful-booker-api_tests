package ru.iukhimenko.restfulbooker;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDatesDTO;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class BookingDataProvider {
    @DataProvider(name = "withAllValues")
    public static Object[][] withAllValues() {
        BookingDTO booking = getBookingDTOWithAllValues();
        return new Object[][] { { booking }};
    }

    @DataProvider(name = "withMandatoryValues")
    public static Object[][] withMandatoryValues() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setAdditionalNeeds(null);
        return new Object[][] { { booking }};
    }

    @DataProvider(name = "withoutMandatoryValue")
    public static Object[][] withoutMandatoryValue() {
        return new Object[][] {
                { withoutFirstName() },
                { withoutLastName() },
                { withoutTotalPrice() },
                { withoutDepositPaid() },
                { withoutCheckIn() },
                { withoutCheckOut() }
        };
    }

    @DataProvider(name = "invalidDateRanges")
    public static Object[][] withInvalidDateRanges() {
        LocalDate checkIn = LocalDate.now().plusMonths(1);
        LocalDate checkOut = checkIn.minusDays(1);
        return new Object[][] {
                { new BookingDatesDTO(checkIn.toString(), checkIn.toString()) },
                { new BookingDatesDTO(checkIn.toString(), checkOut.toString()) }
        };
    }

    public static BookingDTO withoutFirstName() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setFirstName(null);
        return booking;
    }

    public static BookingDTO withoutLastName() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setLastName(null);
        return booking;
    }

    public static BookingDTO withoutTotalPrice() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setTotalPrice(null);
        return booking;
    }

    public static BookingDTO withoutDepositPaid() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setDepositPaid(null);
        return booking;
    }

    public static BookingDTO withoutCheckIn() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.getBookingDates().setCheckin(null);
        return booking;
    }

    public static BookingDTO withoutCheckOut() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.getBookingDates().setCheckOut(null);
        return booking;
    }

    public static BookingDTO getBookingDTOWithAllValues() {
        LocalDate checkInDate = LocalDate.now().plusMonths(1);
        LocalDate checkOutDate = checkInDate.plusDays(7);
        Faker faker = new Faker();
        BookingDTO booking = new BookingDTO();
        booking.setFirstName(faker.name().firstName());
        booking.setLastName(faker.name().lastName());
        booking.setBookingDates(new BookingDatesDTO(checkInDate.toString(), checkOutDate.toString()));
        booking.setDepositPaid(true);
        booking.setTotalPrice(100);
        booking.setAdditionalNeeds("Breakfast");
        return booking;
    }
}
