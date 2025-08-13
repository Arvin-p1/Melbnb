
public class Customer {
    private String name;
    private String surName;
    private String eMail;
    private int numOfGuests;
    private int stayDuration;

    public Customer(String name, String surName, String eMail, int numOfGuests, int stayDuration){//i will have a static methid and i will call that to calculate the stay duration
        this.name = name;
        this.surName = surName;
        this.eMail = eMail;
        this.numOfGuests = numOfGuests;
        this.stayDuration = stayDuration;
    }

    public String getName() {        return name;    }

    public void setName(String name) {        this.name = name;    }

    public String getSurName() {        return surName;    }

    public void setSurName(String surName) {        this.surName = surName;    }

    public String geteMail() {        return eMail;    }

    public void seteMail(String eMail) {        this.eMail = eMail;    }

    public int getNumOfGuests() {        return numOfGuests;    }

    public void setNumOfGuests(int numOfGuests) {        this.numOfGuests = numOfGuests;    }

    public int getStayDuration() {        return stayDuration;    }

    public void setStayDuration(int stayDuration) {        this.stayDuration = stayDuration;    }

    @Override
    public String toString() {
        return "Customer name=" + name + ", surName=" + surName + ", eMail=" + eMail + ", numOfGuests=" + numOfGuests
                + ", stayDuration=" + stayDuration;
    }    
}
