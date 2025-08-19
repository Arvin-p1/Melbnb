import java.util.List;
import java.util.Scanner;

public class CLI {
    private Property currentProperty;
    private List<Property> currentProperties;
    private Booking currentBooking;
    private Customer currentCustomer;
    private String csvPath = "./Melbnb.csv";
    private Scanner scanner = new Scanner(System.in);


    PropertyDatabase propertyDatabase = new PropertyDatabase(csvPath);


    public void run(){
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
            if(choice == -1){continue;}// -1 means that verify int function threw err
            switch (choice){
                case 1:
                searchByLocation();
                break;
                case 2:
                System.out.println("you chose 1");
                break;
                case 3:
                System.out.println("you chose 1");
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

    public void applyBoarders(String message){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> " + message);
        System.out.println("--------------------------------------------------------------------------------");

    }

    public void erorrMessage(String message){
        int totalChars = 80;
        int messageLength = message.length();
        double dynamicLength = (totalChars - messageLength) / 2;
        String dynamicString = " ".repeat((int) Math.ceil(dynamicLength));
        System.out.println("################################################################################");
        System.out.println(dynamicString + message);
        System.out.println("################################################################################");
    }


    public int verifyINT(){
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                erorrMessage("Invalid Input.");
                return -1;
            }
        }
    }

    public String verifyString() {
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


    public void searchByLocation() {
        boolean isRunning = true;
        System.out.print("Please provide a location: ");//moved these out of loop
        String locationInput = verifyString();
        
        while (isRunning) {
            if ("error".equals(locationInput)){continue;} else {
                currentProperties = propertyDatabase.search(locationInput);
            }

            applyBoarders("Select from matching list");
            for (int i = 0; i < currentProperties.size(); i++) {
                Property property = currentProperties.get(i);
                System.out.printf("%d) %s (%s, %s)  Rating: %.1f  $%.2f/night%n",
                        i + 1, property.getName(), property.getType(), property.getLocation(), property.getRating(), property.getPricePerNight());
            }
            int quitBtn = currentProperties.size()+1;
            System.out.println( quitBtn +")" + " Go to main menu");
            System.out.print("Please select: ");
            int choice = verifyINT();
            if ( choice == -1){
                continue;
            } else if (choice == quitBtn) {
                return;
            } else{
                // either
                // currentProperty = currentProperties.get(choice);
                // bookpropertiesfunction(currentProperty);
                // or
                // bookpropertiesfunction(currentProperties.get(choice));
            }
        }
    }

    //If the user chooses not to proceed,the program will go to the main menu
    //If the user chooses to reserve,the program will ask the user to provide personal information
    //The personal information includes given name, surname, email address, and number of guests for the stay.
}
