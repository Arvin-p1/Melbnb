import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Booking {

    private String customerName;
    private String customerEmail;
    private String selectedProperty;
    private String propertyHostName;
    private int numOfGuests;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;
    private boolean isDiscounted;
    private double discountedPricePerNight;
    

    public Booking(String customerName, String customerEmail, String selectedProperty, String propertyHostName, 
            int numOfGuests, LocalDate checkInDate, LocalDate checkOutDate, double totalAmount, boolean isDiscounted, double discountedPricePerNight){

                this.customerName = customerName;
                this.customerEmail = customerEmail;
                this.selectedProperty = selectedProperty;
                this.propertyHostName = propertyHostName;
                this.numOfGuests = numOfGuests;
                this.checkInDate = checkInDate;
                this.checkOutDate = checkOutDate;
                this.totalAmount = totalAmount;
                this.isDiscounted = isDiscounted;
                this.discountedPricePerNight = discountedPricePerNight;
    }


    public String getCustomerName() {        return customerName;    }


    public void setCustomerName(String customerName) {        this.customerName = customerName;    }


    public String getCustomerEmail() {        return customerEmail;    }


    public void setCustomerEmail(String customerEmail) {        this.customerEmail = customerEmail;    }


    public String getSelectedProperty() {        return selectedProperty;    }


    public void setSelectedProperty(String selectedProperty) {        this.selectedProperty = selectedProperty;    }


    public String getPropertyHostName() {        return propertyHostName;    }


    public void setPropertyHostName(String propertyHostName) {        this.propertyHostName = propertyHostName;    }


    public int getNumOfGuests() {        return numOfGuests;    }


    public void setNumOfGuests(int numOfGuests) {        this.numOfGuests = numOfGuests;    }


    public LocalDate getCheckInDate() {        return checkInDate;    }


    public void setCheckInDate(LocalDate checkInDate) {        this.checkInDate = checkInDate;    }


    public LocalDate getCheckOutDate() {        return checkOutDate;    }


    public void setCheckOutDate(LocalDate checkOutDate) {        this.checkOutDate = checkOutDate;    }


    public int getStayDuration() {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalStateException("Dates fields incomplete");
        }
        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (days <= 0)
            throw new IllegalArgumentException("Checkout date must be diffrent to Checkin");
        return (int) days;
    }

    public double getTotalAmount() {        return totalAmount;    }

    public void setTotalAmount(double totalAmount) {        this.totalAmount = totalAmount;    }

    public boolean getIsDiscounted() {        return isDiscounted;    }

    public void setIsDiscounted(boolean isDiscounted) {        this.isDiscounted = isDiscounted;    }

    public double getDiscountedPricePerNight() {        return discountedPricePerNight;    }

    public void setDiscountedPricePerNight(double discountedPricePerNight) {this.discountedPricePerNight = discountedPricePerNight;}
    

    @Override
    public String toString() {
        return "Booking" +
                " Name: " + customerName + 
                " Email: " + customerEmail + 
                " Your Stay: " + selectedProperty + 
                " Your Host" + propertyHostName + 
                " Number Of Guests: " + numOfGuests + 
                " Staying From: " + checkInDate + " - "+ checkOutDate +
                " Total Paid: " + totalAmount;
    }



    // The program will ask the user to reserve the property.
    // The user will be asked to provide check-in and checkout dates.
    // the program will print the information of the selected property
    //
}
