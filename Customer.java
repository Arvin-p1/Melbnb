
public class Customer {
    private String customerName;
    private String customerSurname;
    private String customerEmail;

    public Customer(String customerName, String customerSurname, String customerEmail) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {        return customerName;    }

    public void setCustomerName(String customerName) {        this.customerName = customerName;    }

    public String getCustomerSurname() {        return customerSurname;    }

    public void setCustomerSurname(String customerSurname) {        this.customerSurname = customerSurname;    }

    public String getFullName() {        return customerName + " " + customerSurname;    }

    public String getCustomerEmail() {        return customerEmail;    }

    public void setCustomerEmail(String customerEmail) {        this.customerEmail = customerEmail;    }

    @Override
    public String toString() {
        return "Customer [customerName=" + customerName + ", customerSurname=" + customerSurname + ", customerEmail="
                + customerEmail + "]";
    }
    
    
}
