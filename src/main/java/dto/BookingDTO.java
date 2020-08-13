package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer totalPrice;
    private Boolean depositPaid;
    private BookingDatesDTO bookingDates;
    private String additionalNeeds;

    @JsonInclude(NON_NULL)
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    @JsonProperty("firstname")
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @JsonProperty("firstname")
    public String getFirstName(){
        return this.firstName;
    }

    @JsonProperty("lastname")
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @JsonProperty("lastname")
    public String getLastName(){
        return this.lastName;
    }

    @JsonProperty("totalprice")
    public void setTotalPrice(Integer totalPrice){
        this.totalPrice = totalPrice;
    }

    @JsonProperty("totalprice")
    public Integer getTotalPrice(){
        return this.totalPrice;
    }

    @JsonProperty("depositpaid")
    public void setDepositPaid(Boolean depositPaid){
        this.depositPaid = depositPaid;
    }

    @JsonProperty("depositpaid")
    public Boolean getDepositPaid(){
        return this.depositPaid;
    }

    @JsonProperty("bookingdates")
    public void setBookingDates(BookingDatesDTO bookingDates){
        this.bookingDates = bookingDates;
    }

    @JsonProperty("bookingdates")
    public BookingDatesDTO getBookingDates(){
        return this.bookingDates;
    }

    @JsonProperty("additionalneeds")
    public void setAdditionalNeeds(String additionalNeeds){
        this.additionalNeeds = additionalNeeds;
    }

    @JsonProperty("additionalneeds")
    public String getAdditionalNeeds(){
        return this.additionalNeeds;
    }
}
