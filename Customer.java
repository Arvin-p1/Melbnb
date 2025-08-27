
public class Customer {// blueprint, getters, setters for object instances
    private String customerFullName;
    private String customerEmail;

    public Customer(String customerFullName, String customerEmail) {//constructor method
        this.customerFullName= customerFullName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Customer [customerFullName=" + customerFullName + ", customerEmail=" + customerEmail + "]";
    }

    
    
    
}
