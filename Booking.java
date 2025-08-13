public class Booking {

    private String customerName;
    private String customerEmail;
    private String selectedProperty;
    private String propertyHostName;
    private int numOfGuests;
    private int stayDuration;
    private double totalAmount;
    

    public Booking(String customerName, String customerEmail, String selectedProperty, String propertyHostName, 
            int numOfGuests, int stayDuration, double totalAmount){

                this.customerName = customerName;
                this.customerEmail = customerEmail;
                this.selectedProperty = selectedProperty;
                this.propertyHostName = propertyHostName;
                this.numOfGuests = numOfGuests;
                this.stayDuration = stayDuration;
                this.totalAmount = totalAmount;
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


    public int getStayDuration() {        return stayDuration;    }


    public void setStayDuration(int stayDuration) {        this.stayDuration = stayDuration;    }


    public double getTotalAmount() {        return totalAmount;    }


    public void setTotalAmount(double totalAmount) {        this.totalAmount = totalAmount;    }


    @Override
    public String toString() {
        return "Booking" +
                "Name: " + customerName + 
                "Email: " + customerEmail + 
                "Your Stay: " + selectedProperty + 
                "Your Host" + propertyHostName + 
                "Number Of Guests: " + numOfGuests + 
                "Duration Of Stay: " + stayDuration + 
                ",Total Paid: " + totalAmount;
    }

    // The program will ask the user to reserve the property.
    // The user will be asked to provide check-in and checkout dates.
    // the program will print the information of the selected property
    //
}
