package ru.iukhimenko.restfulbooker;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDatesDTO;
import java.time.LocalDate;
import java.util.function.Consumer;

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
                { remover(bookingDTO -> bookingDTO.setFirstName(null)) },
                { remover(bookingDTO -> bookingDTO.setLastName(null)) },
                { remover(bookingDTO -> bookingDTO.setTotalPrice(null)) },
                { remover(bookingDTO -> bookingDTO.setDepositPaid(null)) },
                { remover(bookingDTO -> bookingDTO.getBookingDates().setCheckin(null)) },
                { remover(bookingDTO -> bookingDTO.getBookingDates().setCheckOut(null)) }
        };
    }

    @DataProvider(name = "withNegativePrice")
    public static Object[][] withNegativePrice() {
        BookingDTO booking = getBookingDTOWithAllValues();
        booking.setTotalPrice(-100);
        return new Object[][] { { booking }};
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

    private static BookingDTO remover(Consumer<BookingDTO> op) {
        BookingDTO bookingDTO = getBookingDTOWithAllValues();
        op.accept(bookingDTO);
        return bookingDTO;
    }
}
