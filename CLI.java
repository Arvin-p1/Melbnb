import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private Property currentProperty;//variables where the selected instances can be stored and accessed
    private List<Property> currentProperties;//list of all filtered objects to be stored and accessed
    private Booking currentBooking;
    private Customer currentCustomer;
    private LocalDate today = LocalDate.now();
    private String csvPath = "./Melbnb.csv";
    private Scanner scanner = new Scanner(System.in);


    PropertyDatabase propertyDatabase = new PropertyDatabase(csvPath);//create instance and load cv


    public void run(){//main method to be called
        boolean isRunning = true;
        System.out.println("Welcome to Melbnb!");
        while(isRunning){
            applyBoarders("Select from main menu");
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
                applyBoarders("Quitting program.");
                isRunning = false;
                return;
                default:
                erorrMessage("Please enter a number between 1 and 4.");
            }
        }
    }

    public void applyBoarders(String message){//formatted messages
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


    public int verifyINT(){//takes input, handels errors and returns feedback
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

    public double verifyDouble() { //takes input, handels errors and returns feedback
        try {
            double input = Double.parseDouble(scanner.nextLine().trim());
            if (input <= 0) {
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

    public String verifyString() {//takes input, cleans it, handels errors and returns feedback
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                erorrMessage("Please enter a non-empty value.");
                return "error";
            } else{
             return input;   
            }
        } catch (Exception e) {
            erorrMessage("Input error.");
            return "error";
        }
    }

    public LocalDate verifyDate() { // takes input, handles errors, returns feedback
    try {

        String input = scanner.nextLine().trim();
        LocalDate dateInput = LocalDate.parse(input);

        if (input.isEmpty()) {
            erorrMessage("Field incomplete.");
            return null;
        } else if (today.isAfter(dateInput)){
            erorrMessage("Date must be in the future.");
            return null;
        } else {
           return dateInput; 
        }
    } catch (DateTimeParseException e) {
        erorrMessage("Invalid date, Use yyyy-mm-dd.");
        return null;
    } catch (Exception e) {
        erorrMessage("Input error.");
        return null;
    }
}

    public void itirateCurrentProperties(){
        for (int i = 0; i < currentProperties.size(); i++) {
            Property property = currentProperties.get(i);
            System.out.printf("%d) %s ( %s, %s )  Rating: %.1f  $%.2f/night%n",
                    i + 1, property.getName(), property.getType(), property.getLocation(), property.getRating(),
                    property.getPricePerNight());
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
            bookProperty();
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

            applyBoarders("Select from matching list");//itirares through list and prints with placeholders
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

            applyBoarders("Browse by type of place");// i have chosen to hardcode the available types
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

            applyBoarders("Select from entire place list");
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
            System.out.print("Please provide the minimum rating: ");
            double rating = verifyDouble();// grabs the min rating
            
            if (rating == -1) {//handles input
                continue;
            } else if (rating > 5){
                erorrMessage("Input must range from 0 to 5");
                continue; 
            }else {
                currentProperties = propertyDatabase.filterByPropertyRating(rating);//store filtered properties within list
            }

            applyBoarders("Select from matching list");
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


    public void bookProperty(){
          
        while(true){

            applyBoarders("Provide dates");
            System.out.print("Check-in (yyyy-mm-dd): ");
            LocalDate checkIn = verifyDate();
            System.out.print("Check-in (yyyy-mm-dd): ");
            LocalDate checkOut = verifyDate();
            if (checkIn == null || checkOut == null) {
                continue; 
            } else if (checkIn.isAfter(checkOut)){
                erorrMessage("Check-in has to be before Check-out");
                continue;
            }

            applyBoarders("Show property details");

        }
    }

    //If the user chooses not to proceed,the program will go to the main menu
    //If the user chooses to reserve,the program will ask the user to provide personal information
    //The personal information includes given name, surname, email address, and number of guests for the stay.
}
 