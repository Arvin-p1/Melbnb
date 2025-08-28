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

    
    //class contructor method
    public Booking(String customerName, String customerEmail, String selectedProperty, String propertyHostName, 
            int numOfGuests, LocalDate checkInDate, LocalDate checkOutDate, double totalAmount, boolean isDiscounted){

                this.customerName = customerName;
                this.customerEmail = customerEmail;
                this.selectedProperty = selectedProperty;
                this.propertyHostName = propertyHostName;
                this.numOfGuests = numOfGuests;
                this.checkInDate = checkInDate;
                this.checkOutDate = checkOutDate;
                this.totalAmount = totalAmount;
                this.isDiscounted = isDiscounted;

    }

    //getter and setter methods
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
        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (days <= 0)
            System.err.println("Checkout date must be diffrent to Checkin");
        return (int) days;
    }

    public double getTotalAmount() {        return totalAmount;    }

    public void setTotalAmount(double totalAmount) {        this.totalAmount = totalAmount;    }

    public boolean getIsDiscounted() {        return isDiscounted;    }

    public void setIsDiscounted(boolean isDiscounted) {        this.isDiscounted = isDiscounted;    }


    //tostring method with stringbuilder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-30s%s%n", "Name:", customerName));
        sb.append(String.format("%-30s%s%n", "Email:", customerEmail) );
        sb.append(String.format("%-30s%s hosted%n", "Your stay:", selectedProperty));
        sb.append(String.format("%-30sby %s%n", "", propertyHostName) );
        sb.append(String.format("%-30s%d%n", "Who's coming:", numOfGuests));
        sb.append(String.format("%-30s%s%n", "Check-in date:", checkInDate));
        sb.append(String.format("%-30s%s%n", "Checkout date:", checkOutDate));
        sb.append(String.format("%-30s$%.2f%n", "Total payment:", totalAmount)); 
        return sb.toString();
    }
}
