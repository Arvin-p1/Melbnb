

public class Property {
    private final String name;
    private final String location;
    private final String description;
    private final String type;
    private final String hostName;
    private final int maximumNumberOfGuests;
    private final double rating;
    private final int pricePerNight;
    private final int serviceFeePerNight;
    private final int cleaningFee;
    private final int weeklyDiscount;

    public Property(String name, String location, String description, String type, String hostName, 
    int maximumNumberOfGuests, int rating, int pricePerNight, int serviceFeePerNight, int cleaningFee, 
    int weeklyDiscount){
        this.name = name;
        this.location = location;
        this.description = description;
        this.type = type;
        this.hostName = hostName;
        this.maximumNumberOfGuests = maximumNumberOfGuests;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.serviceFeePerNight = serviceFeePerNight;
        this.cleaningFee = cleaningFee;
        this.weeklyDiscount = weeklyDiscount;
    }

    public Property(){
        this.name = "Name";
        this.location = "Location";
        this.description = "Description";
        this.type = "Type";
        this.hostName = "Host Name";
        this.maximumNumberOfGuests = 0;
        this.rating = 0;
        this.pricePerNight = 0;
        this.serviceFeePerNight = 0;
        this.cleaningFee = 0;
        this.weeklyDiscount = 0;
    }

    public String getName() {        return name;}

    public String getLocation() {        return location;    }

    public String getDescription() {        return description;    }

    public String getType() {        return type;    }

    public String getHostName() {        return hostName;    }

    public int getMaximumNumberOfGuests() {        return maximumNumberOfGuests;    }

    public double getRating() {        return rating;    }

    public int getPricePerNight() {        return pricePerNight;    }

    public int getServiceFeePerNight() {        return serviceFeePerNight;    }

    public int getCleaningFee() {        return cleaningFee;    }

    public int getWeeklyDiscount() {        return weeklyDiscount;    }

    @Override
    public String toString() {
        return "Property [name=" + name + ", location=" + location + ", description=" + description + ", type=" + type
                + ", hostName=" + hostName + ", maximumNumberOfGuests=" + maximumNumberOfGuests + ", rating=" + rating
                + ", pricePerNight=" + pricePerNight + ", serviceFeePerNight=" + serviceFeePerNight + ", cleaningFee="
                + cleaningFee + ", weeklyDiscount=" + weeklyDiscount + "]";
    }

    
}