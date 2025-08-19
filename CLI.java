import java.util.Scanner;

public class CLI {
    private Property currentProperty;
    private Booking currentBooking;
    private Customer currentCustomer;
    private String csvPath = "./Melbnb.csv";
    private Scanner scanner = new Scanner(System.in);


    PropertyDatabase propertyDatabase = new PropertyDatabase(csvPath);


    public void run(){
        boolean isRunning = true;
        while(isRunning){
            System.out.println("Welcome to Melbnb!");
            applyBoarders("Select from main menu");
            System.out.println(" 1) Search by location");
            System.out.println(" 2) Browse by type of place");
            System.out.println(" 3) Filter by rating");
            System.out.println(" 4) Exit");
            System.out.print("Please select: ");
            int choice = verifyINT();
            if(choice == 0){continue;}
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
                return 0;
            }
        }
    }

    public String verifyString(){
        try{
            return scanner.nextLine().trim();
        } catch (Exception e) {
            System.out.println("Something went wrong. Please try again.");
            return "error";
        }
    }


    public void searchByLocation() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("Please provide a location: ");
            String location = verifyString();
            if (location.contains("error")){continue;} else {
                propertyDatabase.search(location);
            }
            //Property results = 
            applyBoarders("Select from matching list");
            
            switch () {
                case 1:
                    
                    break;
            
                default:
                    break;
            }
        }
    }

    //If the user chooses not to proceed,the program will go to the main menu
    //If the user chooses to reserve,the program will ask the user to provide personal information
    //The personal information includes given name, surname, email address, and number of guests for the stay.
}
