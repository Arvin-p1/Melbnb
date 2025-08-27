import java.time.LocalDate;//imports
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private List<Property> currentProperties;// list of all filtered objects to be stored and accessed
    private Property currentProperty;//variables where the selected instances can be stored and accessed
    private Customer currentCustomer;
    private Booking currentBooking;
    private CostCalculations currentCostCalculations;
    private LocalDate today = LocalDate.now();
    private String csvPath = "./Melbnb.csv";
    private Scanner scanner = new Scanner(System.in);


///////////////////////////////////////Helper Functions Section///////////////////////////////////////////////

    public void messageWithBorders(String message){//formatted messages
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> " + message);
        System.out.println("--------------------------------------------------------------------------------");

    }

    public void errorMessage(String message){//formatted errors
        int totalChars = 80;//length of borders
        int messageLength = message.length();
        double dynamicLength = (totalChars - messageLength) / 2;//how many spaces it takes to centre message
        String dynamicString = " ".repeat((int) Math.ceil(dynamicLength));
        System.out.println("################################################################################");
        System.out.println(dynamicString + message);
        System.out.println("################################################################################");
    }


    public int verifyINT(){//takes input int, handels errors and returns feedback or value
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());//for err handeling and residue
                if (input <= 0){
                    errorMessage("Input must be greater than 0.");
                    return -1;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                errorMessage("Invalid Input.");
                return -1;
            }
    }

    public double verifyDouble() { //takes input double, handels errors, returns feedback or value
        try {
            double input = Double.parseDouble(scanner.nextLine().trim());
            if (input < 0) {
                errorMessage("Input must be greater than 0.");
                return -1;
            } else {
                return input;
            }
        } catch (NumberFormatException e) {
            errorMessage("Invalid input.");
            return -1;
        }
    }

    public String verifyString() {//takes input string, cleans it, handels errors, returns feedback or value
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                errorMessage("Field cannot be empty.");
                return "error";
            } else{
             return input;   
            }
        } catch (Exception e) {
            errorMessage("Input error.");
            return "error";
        }
    }

    public String verifyEmail() {// takes input email string, cleans it, handels errors and returns feedback
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                errorMessage("Please enter a non empty value.");
                return "error";
            } else if (!input.contains("@") || !input.contains(".com")) {
                errorMessage("Field cannot be empty.");
                return "error";
            } else {
                return input;
            }
        } catch (Exception e) {
            errorMessage("Input error.");
            return "error";
        }
    }

    public boolean verifyBoolean() {//takes input boolean, cleans it, handels errors and returns feedback
        // didnt Boolean.parseBoolean() because i couldnt compare other values
        try {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("false") || input.equals("n") || input.equals("no")) {
                return false;
            } else {
                errorMessage("Wrong input.");
                return false;
            }
        } catch (Exception e) {
            errorMessage("Wrong input.");
            return false;
        }
    }

    public LocalDate verifyDate() { //takes input datetime, handles errors, returns feedback
        try {

            String input = scanner.nextLine().trim();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate FormattedDate = LocalDate.parse(input, format);

            if (input.isEmpty()) {
                errorMessage("Field incomplete.");
                return null;
            } else if (today.isAfter(FormattedDate)){
                errorMessage("Date must be in the future.");
                return null;
            } else {
            return FormattedDate; 
            }
        } catch (DateTimeParseException e) {
            errorMessage("Invalid date, Use dd/MM/yyyy.");
            return null;
        } catch (Exception e) {
            errorMessage("Wrong input.");
            return null;
        }
    }

    public void itirateCurrentProperties(){//prints list of properties, streamlined repetetive code
        for (int i = 0; i < currentProperties.size(); i++) {
            Property property = currentProperties.get(i);
            System.out.printf("%d) %s 👤:%d, 💳:$%.2f/🌘%n",
                    i + 1, property.getName(), property.getMaximumNumberOfGuests(), 
                    property.getPricePerNight());
        }
        
    }

    public List<String> itiratePropertyTypes() {// prints and returns a list of property types

        List<String> matchedTypes = new ArrayList<>();

        for (Property property : currentProperties) {//grab types
            if (!matchedTypes.contains(property.getType())) {
                matchedTypes.add(property.getType());
            }
        }
        for (int i = 0; i < matchedTypes.size(); i++) {//print types in numbered format
            String type = matchedTypes.get(i);
            System.out.printf("%d) %s%n", i +1, type);
        }
        return matchedTypes;
    }

    public int handleUserChoice(int choice, int quitBtn){//streamlined repetetive code, returns feedback
        // calls for booking details after a property has been selected in any of main methods
        //-1 == err, -2 == quit, -3 == property has been selected
        if (choice == -1) {
            return -1;
        } else if (choice == quitBtn) {
            return -2;
        } else if (choice > quitBtn) {
            errorMessage("Input must range from 1 to " + quitBtn);
            return -1;
        } else {
            currentProperty = currentProperties.get(choice -1 );//grab property at that index of list
            getBookingDetails();
            return -3;
        }
    }

    /////////////////////////////////CLI driver functions///////////////////////////////////////////

    PropertyDatabase propertyDatabase = new PropertyDatabase(csvPath);// create instance and load cv

    public void run() {// main method to be called

        System.out.println("Welcome to Melbnb!");//only print on first time

        while (true) {
            messageWithBorders("Select from main menu");
            System.out.println(" 1) Search by location");
            System.out.println(" 2) Browse by type of place");
            System.out.println(" 3) Filter by rating");
            System.out.println(" 4) Exit");
            System.out.print("Please select: ");
            int choice = verifyINT();//read int
            if (choice == -1) {continue;} // if the input is wrong then continue

            switch (choice) {
                case 1:
                    searchByLocation();
                    break;
                case 2:
                    searchByType();
                    break;
                case 3:
                    searchByRating();
                    break;
                case 4:
                    messageWithBorders("Quitting program.");
                    return;

                default:
                    errorMessage("Please enter a number between 1 and 4.");
            }
        }
    }


    public void searchByLocation() {//filters properties by location
        
        while (true) {

            System.out.print("Please provide a location: ");
            String locationInput = verifyString();//grab String input

            if ("error".equals(locationInput)){continue;} else {//if verifyDtring returns error
                currentProperties = propertyDatabase.search(locationInput);//store filtered properties within list
            }

            messageWithBorders("Select from matching list");//itirares through list and prints with placeholders
            itirateCurrentProperties();

            int quitBtn = currentProperties.size()+1;//dynamic numbering of quit btn
            System.out.println( quitBtn +") Go to main menu");
            System.out.print("Please select: ");
             
            int choice = verifyINT();//grab input

            int verifyChoice = handleUserChoice(choice, quitBtn);

            if (verifyChoice == -1) {// -1 == err
                continue;
            } else if (verifyChoice == -2) { // -2 == quitbtn
                return;
            } else if (verifyChoice == -3) { // -3 == the next function has been called
                break;
            }
        }
    }


    public void searchByType () {//filters properties by type

        while (true) {

            messageWithBorders("Browse by type of place");
            currentProperties = propertyDatabase.getProperties();//load all properties
            List<String> allPropertyTypes = itiratePropertyTypes();//prints and returns a list of types

            int quitBtn = allPropertyTypes.size() + 1;//dynamic quit btn
            System.out.println(quitBtn + ") Go to main menu");

            System.out.print("Please select: ");//grabs input and verifies
            int userInput = verifyINT();
            if (userInput == -1) {
                continue;
            } else if (userInput == quitBtn) {
                return;
            } else if (userInput > quitBtn) {
                errorMessage("Input must range from 1 to " + quitBtn);
                continue;
            }

            messageWithBorders("Select from entire place list");//displays properties of selected type
            String selectedPropertyType = allPropertyTypes.get(userInput -1);
            currentProperties  = propertyDatabase.filterByPropertyType(selectedPropertyType);
            itirateCurrentProperties();//itirates and prints all filtered properties

            quitBtn = currentProperties.size() + 1;//update value
            System.out.println(quitBtn + ") Go to main menu");

            System.out.print("Please select: ");//grab input
            int choice = verifyINT();

            int handleChoice = handleUserChoice(choice, quitBtn);//process input
            if (handleChoice == -1) {// -1 == err
                continue;
            } else if (handleChoice == -2) { // -2 == quitbtn
                return;
            } else if (handleChoice == -3) { // -3 == the next function has been called
                break;
            }
        }
    }

    public void searchByRating() {//filters properties by rating

        while (true) {
            System.out.print("Please provide the minimum rating (0-5): ");
            double rating = verifyDouble();// grabs the min rating
            
            if (rating == -1) {//handles input
                continue;
            } else if (rating > 5){
                errorMessage("Input must range from 0 to 5");
                continue; 
            }else {
                currentProperties = propertyDatabase.filterByPropertyRating(rating);//store filtered properties within list
            }

            messageWithBorders("Select from matching list");
            itirateCurrentProperties();

            int quitBtn = currentProperties.size() + 1;
            System.out.println(quitBtn + ") Go to main menu");
            System.out.print("Please select: ");

            int choice = verifyINT();
            int verifyChoice = handleUserChoice(choice, quitBtn);

            if (verifyChoice == -1) {
                continue;
            } else if (verifyChoice == -2) {
                return;
            } else if (verifyChoice == -3) {
                break;
            }
        }
    }


    public void getBookingDetails(){// grabs and veifies booking details, prints booking details w/o confirmation
        
        while(true){

            messageWithBorders("Provide dates");//grabs the dates
            System.out.print("Please provide check-in date (dd/mm/yyyy): ");
            LocalDate checkIn = verifyDate();
            System.out.print("Please provide checkout date (dd/mm/yyyy): ");
            LocalDate checkOut = verifyDate();

            if (checkIn == null || checkOut == null) {//checks for correct inputs
                continue; 
            } else if (checkIn.isAfter(checkOut)){
                errorMessage("Check-in has to be before Check-out");
                continue;
            }

            currentBooking = new Booking( "", "", currentProperty.getName(),//make new booking instance with some empty fields
            currentProperty.getHostName(), 1, checkIn, checkOut, 0.0, false);

            currentCostCalculations = new CostCalculations(currentProperty, currentBooking);//get the calculations of the booking and property
            currentCostCalculations.applyPricing();//sets values for booking.total and booking.isdiscounted

            messageWithBorders("Show property details");//print the property and cost breakdowns
            System.out.println(currentProperty);
            System.out.print(currentCostCalculations);
            System.out.println("Would you like to reserve the property (Y/N)? ");//confirm if they want to book
            boolean isReserved = verifyBoolean(); if (!isReserved){ 
                return;
            } else {
                bookProperty();
                break;
            }


        }
    }

    public void bookProperty(){//grabs final details and confirms before grabbing payment

        while(true){

            messageWithBorders("Provide personal information");//grab info and veify right away
            System.out.print("Please provide your given name: ");
            String customerFirstName = verifyString();
            System.out.print("Please provide your surname: ");
            String customerLastName = verifyString();
            System.out.print("Please provide your email address: ");
            String customerEmail = verifyEmail();
            if ("error".equals(customerFirstName) || "error".equals(customerLastName) || "error".equals(customerEmail)) {
                errorMessage("Incorrect input.");
                continue;
            }

            System.out.print("Please provide number of guests: ");//ensure the !guests coming > space availabe 
            int NumOfGuests = verifyINT();
            int guestCapacity = currentProperty.getMaximumNumberOfGuests();
            if (NumOfGuests > guestCapacity) {
                errorMessage("Property guest capacity is " + guestCapacity);
                continue;
            }

            System.out.println("Confirm and pay (Y/N): ");//confirm booking or return to main menu
            boolean isConfirmed = verifyBoolean();
            if (!isConfirmed) {
                return;
            } else {
                currentCustomer = new Customer(customerFirstName + " " + customerLastName, customerEmail);
                currentBooking.setCustomerName(currentCustomer.getCustomerFullName());
                currentBooking.setCustomerEmail(currentCustomer.getCustomerEmail());
                currentBooking.setNumOfGuests(NumOfGuests);
                currentBooking.setTotalAmount(currentCostCalculations.grandTotal());
            }

            messageWithBorders( "Your trip is booked. A receipt has been sent to your email.\r\n" +
                                "  Details of your trip are shown below.\r\n" +
                                "  Your host will contact you before your trip. Enjoy your stay!");

            System.out.println(currentBooking);//print booking after confirmation msg
            return;
        }
    }
}
 