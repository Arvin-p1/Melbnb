public class Property {//blueprint, getters, setters for object instances
    private final String name;
    private final String location;
    private final String description;
    private final String type;
    private final String hostName;
    private final int maximumNumberOfGuests;
    private final double rating;
    private final double pricePerNight;
    private final double serviceFeePerNight;
    private final double cleaningFee;
    private final int weeklyDiscount;

    public Property(String name, String location, String description, String type, String hostName, //class constructor
    int maximumNumberOfGuests, double rating, double pricePerNight, double serviceFeePerNight, double cleaningFee, 
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

    //getters and setters
    public String getName() {        return name;}

    public String getLocation() {        return location;    }

    public String getDescription() {        return description;    }

    public String getType() {        return type;    }

    public String getHostName() {        return hostName;    }

    public int getMaximumNumberOfGuests() {        return maximumNumberOfGuests;    }

    public double getRating() {        return rating;    }

    public double getPricePerNight() {        return pricePerNight;    }

    public double getServiceFeePerNight() {        return serviceFeePerNight;    }

    public double getCleaningFee() {        return cleaningFee;    }

    public int getWeeklyDiscount() {        return weeklyDiscount;    }

    //to string with stringbuilder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-30s%s hosted%n", "Property:", name));
        sb.append(String.format("%-30sby %s%n", "", hostName) );
        sb.append(String.format("%-30s%s%n", "Type of place:", type));
        sb.append(String.format("%-30s%s%n", "Location:", location));
        sb.append(String.format("%-30s%.2f%n", "Rating:", rating));
        sb.append(String.format("%-30s%s%n", "Description:", description));
        sb.append(String.format("%-30s%d%n", "Guests capacity:", maximumNumberOfGuests)); 
        return sb.toString();
    }
    
} 