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

    private static String formatRow(String label, String value) {//created my own formatting

        String left = String.format("%-30s", label);//string on left side with 30"" gap

        value = value.replaceAll("(.{1,50})(\\s|$)", "$1\n").trim().replaceAll("\n", "\n" + " ".repeat(30));
        //find just one charachter from 1 to 50 characters then 
        // n{x,y} Matches any string that contains a sequence of X to Y n's
        //.	Find just one instance of any character
        // $ Finds a match at the end of the string
        // |or \s Find a whitespace character
        // to represent the literal \s in a regex pattern, you must write \\s
        return left + value + "\n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(formatRow("Property:", name + " hosted"));
        sb.append(formatRow("", "by " + hostName));
        sb.append(formatRow("Type of place:", type));
        sb.append(formatRow("Location:", location));
        sb.append(formatRow("Rating:", String.format("%.2f/5", rating)));//turnm into string
        sb.append(formatRow("Description:", description));
        sb.append(formatRow("Guests capacity:", String.valueOf(maximumNumberOfGuests)));
        return sb.toString();
    }
    
} 