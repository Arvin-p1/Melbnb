import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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


    PropertyDatabase propertyDatabase = new PropertyDatabase(csvPath);//create instance and load cv


    public void run(){//main method to be called
        boolean isRunning = true;
        System.out.println("Welcome to Melbnb!");
        while(isRunning){
            applyBorders("Select from main menu");
            System.out.println(" 1) Search by location");
            System.out.println(" 2) Browse by type of place");
            System.out.println(" 3) Filter by rating");
            System.out.println(" 4) Exit");
            System.out.print("Please select: ");
            int choice = verifyINT();
            if(choice == -1){continue;}//if the input is wrong then continue
            switch (choice){
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
                applyBorders("Quitting program.");
                isRunning = false;
                return;
                default:
                erorrMessage("Please enter a number between 1 and 4.");
            }
        }
    }

    public void applyBorders(String message){//formatted messages
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> " + message);
        System.out.println("--------------------------------------------------------------------------------");

    }

    public void erorrMessage(String message){//formatted errors
        int totalChars = 80;//length of borders
        int messageLength = message.length();
        double dynamicLength = (totalChars - messageLength) / 2;//how many spaces it takes to centre message
        String dynamicString = " ".repeat((int) Math.ceil(dynamicLength));
        System.out.println("################################################################################");
        System.out.println(dynamicString + message);
        System.out.println("################################################################################");
    }


    public int verifyINT(){//takes input int, handels errors and returns feedback
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());//using linereader than parsing for err handeling and residue
                if (input <= 0){
                    erorrMessage("Input must be greater than 0.");
                    return -1;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                erorrMessage("Invalid Input.");
                return -1;
            }
    }

    public double verifyDouble() { //takes input double, handels errors and returns feedback
        try {
            double input = Double.parseDouble(scanner.nextLine().trim());
            if (input < 0) {
                erorrMessage("Input must be greater than 0.");
                return -1;
            } else {
                return input;
            }
        } catch (NumberFormatException e) {
            erorrMessage("Invalid input.");
            return -1;
        }
    }

    public String verifyString() {//takes input string, cleans it, handels errors and returns feedback
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                erorrMessage("Field cannot be empty.");
                return "error";
            } else{
             return input;   
            }
        } catch (Exception e) {
            erorrMessage("Input error.");
            return "error";
        }
    }

    public boolean verifyBoolean() {//takes input boolean, cleans it, handels errors and returns feedback
        try {
            String input = scanner.nextLine().trim().toLowerCase();//didnt Boolean.parseBoolean() because i couldnt compare other values

            if (input.equals("true") || input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("false") || input.equals("n") || input.equals("no")) {
                return false;
            } else {
                erorrMessage("Wrong input.");
                return false;
            }
        } catch (Exception e) {
            erorrMessage("Wrong input.");
            return false;
        }
    }

    public LocalDate verifyDate() { // takes input datetime, handles errors, returns feedback
        try {

            String input = scanner.nextLine().trim();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate FormattedDate = LocalDate.parse(input, format);

            if (input.isEmpty()) {
                erorrMessage("Field incomplete.");
                return null;
            } else if (today.isAfter(FormattedDate)){
                erorrMessage("Date must be in the future.");
                return null;
            } else {
            return FormattedDate; 
            }
        } catch (DateTimeParseException e) {
            erorrMessage("Invalid date, Use dd/MM/yyyy.");
            return null;
        } catch (Exception e) {
            erorrMessage("Wrong input.");
            return null;
        }
    }

    public String verifyEmail() {// takes input email string, cleans it, handels errors and returns feedback
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                erorrMessage("Please enter a non empty value.");
                return "error";
            } else if (!input.contains("@") || !input.contains(".com")){
                erorrMessage("Field cannot be empty.");
                return "error";
            } else {
                return input;
            }
        } catch (Exception e) {
            erorrMessage("Input error.");
            return "error";
        }
    }

    public void itirateCurrentProperties(){
        for (int i = 0; i < currentProperties.size(); i++) {
            Property property = currentProperties.get(i);
            System.out.printf("%d) %s ( %s, %s, guests: %d )  Rating: %.1f  $%.2f/night%n",
                    i + 1, property.getName(), property.getType(), property.getLocation(), 
                    property.getMaximumNumberOfGuests(), property.getRating(),property.getPricePerNight());
        }
        
    }

    public int handleUserChoice(int choice, int quitBtn){
        if (choice == -1) {
            return -1;
        } else if (choice == quitBtn) {
            return -2;
        } else if (choice > quitBtn) {
            erorrMessage("Input must range from 1 to " + quitBtn);
            return -1;
        } else {
            currentProperty = currentProperties.get(choice -1 );
            getBookingDetails();
            return -3;
        }
    }


    public void searchByLocation() {//filters properties by location
        
        while (true) {//dont see a reason to break loop atm

            System.out.print("Please provide a location: ");
            String locationInput = verifyString();

            if ("error".equals(locationInput)){continue;} else {//check for errors 
                currentProperties = propertyDatabase.search(locationInput);//store filtered properties within list
            }

            applyBorders("Select from matching list");//itirares through list and prints with placeholders
            itirateCurrentProperties();

            int quitBtn = currentProperties.size()+1;//dynamic numbering of quit btn
            System.out.println( quitBtn +") Go to main menu");
            System.out.print("Please select: ");

            int choice = verifyINT();
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

            applyBorders("Browse by type of place");// i have chosen to hardcode the available types
            System.out.println(" 1) Private room");// still works perfectly with current data set but not dynamic
            System.out.println(" 2) Entire place");
            System.out.println(" 3) Shared room");
            System.out.println(" 4) Go to main menu");


            System.out.print("Please select: ");//grabs type choice and verifies
            int userInput = verifyINT();
            if (userInput == -1) {
                continue;
            } else if (userInput == 4) {
                return;
            } else if (userInput > 4) {
                erorrMessage("Input must range from 1 to 4");
                continue;
            }

            String type = "";//prepares type based on hadcoded choice
            if (userInput == 1){
                type = "Private room";
            } else if (userInput == 2){
                type = "Entire place";
            } else if (userInput == 3){
                type = "Shared room";
            }

            applyBorders("Select from entire place list");
            currentProperties  = propertyDatabase.filterByPropertyType(type);//grabs the list of filtered properties
            itirateCurrentProperties();//itirates and prints all filtered properties

            int quitBtn = currentProperties.size() + 1;
            System.out.println(quitBtn + ") Go to main menu");
            System.out.print("Please select: ");

            int choice = verifyINT();
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

    public void searchByRating() {//filters properties by rating

        while (true) {
            System.out.print("Please provide the minimum rating (0-5): ");
            double rating = verifyDouble();// grabs the min rating
            
            if (rating == -1) {//handles input
                continue;
            } else if (rating > 5){
                erorrMessage("Input must range from 0 to 5");
                continue; 
            }else {
                currentProperties = propertyDatabase.filterByPropertyRating(rating);//store filtered properties within list
            }

            applyBorders("Select from matching list");
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

            applyBorders("Provide dates");//grabs the dates
            System.out.print("Please provide check-in date (dd/mm/yyyy): ");
            LocalDate checkIn = verifyDate();
            System.out.print("Please provide checkout date (dd/mm/yyyy): ");
            LocalDate checkOut = verifyDate();

            if (checkIn == null || checkOut == null) {//checks for correct inputs
                continue; 
            } else if (checkIn.isAfter(checkOut)){
                erorrMessage("Check-in has to be before Check-out");
                continue;
            }

            currentBooking = new Booking( "", "", currentProperty.getName(),//make new booking instance with some empty fields
            currentProperty.getHostName(), 1, checkIn, checkOut, 0.0, false);

            currentCostCalculations = new CostCalculations(currentProperty, currentBooking);//get the calculations of the booking and property
            currentCostCalculations.applyPricing();//sets values for booking.total and booking.isdiscounted

            applyBorders("Show property details");//print the property and cost breakdowns
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

            applyBorders("Provide personal information");//grab info and veify right away
            System.out.print("Please provide your given name: ");
            String customerFirstName = verifyString();
            System.out.print("Please provide your surname: ");
            String customerLastName = verifyString();
            System.out.print("Please provide your email address: ");
            String customerEmail = verifyEmail();
            if ("error".equals(customerFirstName) || "error".equals(customerLastName) || "error".equals(customerEmail)) {
                erorrMessage("Incorrect input.");
                continue;
            }

            System.out.print("Please provide number of guests: ");//ensure the !guests coming > space availabe 
            int NumOfGuests = verifyINT();
            int guestCapacity = currentProperty.getMaximumNumberOfGuests();
            if (NumOfGuests > guestCapacity) {
                erorrMessage("Property gues capacity is " + guestCapacity);
                continue;
            }

            System.out.println("Confirm and pay (Y/N): ");
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

            applyBorders("Your trip is booked. A receipt has been sent to your email.\r\n" +
                    " Details of your trip are shown below.\r\n" +
                    " Your host will contact you before your trip. Enjoy your stay!");

            System.out.println(currentBooking);
        }
    }

    //If the user chooses not to proceed,the program will go to the main menu
    //If the user chooses to reserve,the program will ask the user to provide personal information
    //The personal information includes given name, surname, email address, and number of guests for the stay.
}
 