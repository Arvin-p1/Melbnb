public final class CostCalculations {//holds all cost calculation related functions that i could think of but yet to be implimented
    private static final int week = 7;
    private Property property;
    private Booking booking;
    private int stayDuration;

    
    public CostCalculations(Property property, Booking booking) {// class constructor method
        this.property = property;
        this.booking = booking;
        this.stayDuration = booking.getStayDuration();
    }

    //Getters and setters
    public static int getWeek() {        return week;    }


    public Property getProperty() {        return property;    }


    public void setProperty(Property property) {        this.property = property;    }


    public Booking getBooking() {        return booking;    }


    public void setBooking(Booking booking) {        this.booking = booking;    }


    public int getStayDuration() {        return stayDuration;    }


    public void setStayDuration(int stayDuration) {        this.stayDuration = stayDuration;    }




    public boolean isDiscounted() {//checks if a property dicount eligable 
        if (stayDuration >= week && property.getWeeklyDiscount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public double baseNightly() {//price per night might change
        return property.getPricePerNight();
    }


    public double nightlyRateAfterDiscount() {//discounted rate per night
        double basePrice = baseNightly();
        if (isDiscounted()) {
            return basePrice * (1 - (property.getWeeklyDiscount() / 100.0));
        }
        return basePrice;
    }


    public double accommodationTotalNoDiscount() {//total withoutdiscount
        return baseNightly() * stayDuration;
    }


    public double accommodationTotalWithDiscount() {//total before discount
        return nightlyRateAfterDiscount() * stayDuration;
    }

    public double serviceFeeTotal() {//total service fee
        return property.getServiceFeePerNight() * stayDuration;
    }

    public double cleaningFee() {//cleaningfee
        return property.getCleaningFee();
    }


    public double grandTotal() {//grabs total
        return accommodationTotalWithDiscount() + serviceFeeTotal() + cleaningFee();
    }

 
    public void applyPricing() {

        booking.setTotalAmount(grandTotal());//set all financial bookingfields
        boolean isDiscounted = isDiscounted();
        booking.setIsDiscounted(isDiscounted);
    }

    //dynamic to string method. it prints discounted price only if discounted
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isDiscounted()){
            sb.append(String.format("%-30s$%.2f ($%.2f * %d nights)%n", "Room price:", accommodationTotalNoDiscount(), baseNightly(), stayDuration));
            sb.append(String.format("%-30s$%.2f ($%.2f * %d nights)%n", "Discounted room price:", accommodationTotalWithDiscount(), nightlyRateAfterDiscount(), stayDuration));
        } else{
            sb.append(String.format("%-30s$%.2f ($%.2f * %d nights)%n", "Room price:", accommodationTotalNoDiscount(), baseNightly(), stayDuration));
        }
        sb.append(String.format("%-30s$%.2f%n", "Service fee:", serviceFeeTotal()));
        sb.append(String.format("%-30s$%.2f%n", "Cleaning fee:", cleaningFee()));
        sb.append(String.format("%-30s$%.2f%n", "Total:", grandTotal()));

        return sb.toString();
    }
}

