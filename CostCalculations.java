public final class CostCalculations {
    
    private static final int week = 7;
    
    private CostCalculations() {}//empty constructor


    public static int stayDuration(Booking booking) {
        return booking.getStayDuration(); // will throw if dates invalid (good)
    }

    public static boolean isDiscounted(Property property, Booking booking) {
        if (stayDuration(booking) >= week && property.getWeeklyDiscount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static double baseNightly(Property property) {//price per night
        return property.getPricePerNight();
    }


    public static double nightlyRateAfterDiscount(Property property, Booking booking) {
        double basePrice = baseNightly(property);
        if (isDiscounted(property, booking)) {
            return basePrice * (1 - (property.getWeeklyDiscount() / 100.0));
        }
        return basePrice;
    }


    public static double accommodationTotalNoDiscount(Property property, Booking booking) {//total withoutdiscount
        return baseNightly(property) * stayDuration(booking);
    }


    public static double accommodationTotalWithDiscount(Property property, Booking booking) {
        return nightlyRateAfterDiscount(property, booking) * stayDuration(booking);
    }

    public static double serviceFeeTotal(Property property, Booking booking) {
        return property.getServiceFeePerNight() * stayDuration(booking);
    }

    public static double cleaningFee(Property property) {
        return property.getCleaningFee();
    }


    public static double grandTotal(Property property, Booking booking) {
        return accommodationTotalWithDiscount(property, booking)
                + serviceFeeTotal(property, booking)
                + cleaningFee(property);
    }

 
    public static void applyPricing(Property property, Booking booking) {//ties everything together for less cluter
        boolean discount = isDiscounted(property, booking);
        booking.setIsDiscounted(discount);
        booking.setDiscountedPricePerNight(nightlyRateAfterDiscount(property, booking));
        booking.setTotalAmount(grandTotal(property, booking));
    }
}


//Cleaning fee is one-off. 
//Weekly discount is only applied to the price per night if the period of the stay is equal to or over 7 nights.
//There is no discount for service fee per night and cleaning fee.
//For example, if a user books a property for 7 nights,the total payment with discount is calculated below:
// (Price per night ∗ ((100 − Weekly discount) / 100) + Service fee per night) ∗ 7 + Cleaning fee
