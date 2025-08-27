import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class PropertyDatabase implements Searchable<Property> {
    private ArrayList<Property> properties = new ArrayList<>();//Holds all properties


    public PropertyDatabase(String csvFilePath){//class contructor method which loads cvs after being initiated
        loadCSV(csvFilePath);
    }


        @Override
    public List<Property> search(String location) {//filters for location but must be called search because of interface template
        List<Property> matchedProperties = new ArrayList<>();

        for (Property property : properties){
            if (property.getLocation().toLowerCase().contains(location)){
                matchedProperties.add(property);
            }
        }
        if(matchedProperties.size() == 0){
            System.err.println("0 Matches found.");
        }
        return matchedProperties;//returns an empty list or a list of mateched propety objects
    }

    public List<Property> filterByPropertyType(String keyword){//filters by type category
        String type = keyword.trim().toLowerCase();

        List<Property> matchedProperties = new ArrayList<>();

        for (Property property : properties){
            if (property.getType().toLowerCase().contains(type)){
                matchedProperties.add(property);
            }
        }
        if (matchedProperties.size() == 0) {
            System.err.println("0 Matches found.");
        }
        return matchedProperties;
    }

    public List<Property> filterByPropertyRating(double rating){//had to use Double rather than double to compare to null

        List<Property> matchedProperties = new ArrayList<>();//polymorphism

        for (Property property : properties){
            if (property.getRating() >= rating){
                matchedProperties.add(property);
            }
        }
        if (matchedProperties.size() == 0) {
            System.err.println("0 Matches found.");
        }
        return matchedProperties;
    }

    public void loadCSV(String csvFilePath){//will be called in class constructor method
        File file = new File(csvFilePath);

        try( Scanner scanner = new Scanner(file)){//scanner will read the file or throw err
            scanner.nextLine(); //skip first line aka header row

            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");//read and split line and values then store all in array

                String name = columns[0];
                String location = columns[1];
                String description = columns[2];
                String type = columns[3];
                String hostName = columns[4];
                int maxGuests = Integer.parseInt(columns[5]);
                double rating = Double.parseDouble(columns[6]);
                double pricePerNight = Double.parseDouble(columns[7]);
                double serviceFeePerNight = Double.parseDouble(columns[8]);
                double cleaningFee = Double.parseDouble(columns[9]);
                int weeklyDiscount = Integer.parseInt(columns[10]);
                

                Property property = new Property(//create property object as we itirate
                        name, location, description, type, hostName,
                        maxGuests, rating, pricePerNight,
                        serviceFeePerNight, cleaningFee, weeklyDiscount);

                properties.add(property);//add each instance
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found at : " + csvFilePath);
        }
    }

    public ArrayList<Property> getProperties() {//getter that returns all propeties
        return properties;
    }

}

