import java.util.List;
import java.util.Scanner;

public class CLI {
    private Property currentProperty;//variables where the selected instances can be stored and accessed
    private List<Property> currentProperties;//list of all filtered objects to be stored and accessed
    private Booking currentBooking;
    private Customer currentCustomer;
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


    public void searchByLocation() {//filters properties by location

        System.out.print("Please provide a location: "); 
        String locationInput = verifyString();//moved outside the loop for functional purposes
        
        while (true) {//dont see a reason to break loop atm

            if ("error".equals(locationInput)){continue;} else {//check for errors 
                currentProperties = propertyDatabase.search(locationInput);//store filtered properties within list
            }

            applyBoarders("Select from matching list");//itirares through list and prints with placeholders
            for (int i = 0; i < currentProperties.size(); i++) {
                Property property = currentProperties.get(i);
                System.out.printf("%d) %s (%s, %s)  Rating: %.1f  $%.2f/night%n",
                        i + 1, property.getName(), property.getType(), property.getLocation(), property.getRating(), property.getPricePerNight());
            }

            int quitBtn = currentProperties.size()+1;//dynamic numbering of quit btn
            System.out.println( quitBtn +") Go to main menu");
            System.out.print("Please select: ");

            int choice = verifyINT();//takes input, verifies it then handels accordingly
            if ( choice == -1 ){//if there was an err
                continue;
            } else if (choice == quitBtn) {//if quit
                return;
            } else if (choice > quitBtn) {//if input not in range of options
                erorrMessage("Input must range from 1 to " + quitBtn);
                continue;
            }else{//proceeds to booking selected property
                System.out.println("you have chosen a property");
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
