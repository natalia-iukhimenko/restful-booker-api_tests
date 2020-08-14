package ru.iukhimenko.restfulbooker.dto.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDatesDTO {
    private String checkIn;
    private String checkOut;

    public BookingDatesDTO() {
    }

    public BookingDatesDTO(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @JsonProperty("checkin")
    public void setCheckin(String checkIn){
        this.checkIn = checkIn;
    }

    @JsonProperty("checkin")
    public String getCheckIn(){
        return this.checkIn;
    }

    @JsonProperty("checkout")
    public void setCheckOut(String checkOut){
        this.checkOut = checkOut;
    }

    @JsonProperty("checkout")
    public String getCheckOut(){
        return this.checkOut;
    }


    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;
        BookingDatesDTO bookingDates = (BookingDatesDTO)object;
        return (bookingDates != null && checkIn.equals(bookingDates.checkIn) && checkOut.equals(bookingDates.checkOut));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checkIn == null) ? 0 : checkIn.hashCode()) + ((checkOut == null) ? 0 : checkOut.hashCode());
        return result;
    }
}
