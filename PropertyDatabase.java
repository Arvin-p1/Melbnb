import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PropertyDatabase implements Searchable<Property> {
    private ArrayList<Property> properties = new ArrayList<>();

    public List<Property> listAllProperties(){
        return properties;
    }
        @Override
    public List<Property> search(String keyword) {
        String k = keyword.toLowerCase().trim();
        List<Property> out = new ArrayList<>();
        for (Property p : properties) {
            if (p.getLocation().toLowerCase().contains(k)) {
                out.add(p);
            }
        }
        return out;
    }

    public void loadProperties(){
        String csvFilePath = "./Melbnb.csv";
        File file = new File(csvFilePath);


        try( Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(",");

            while (scanner.hasNextLine()){
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
    }
}
    // search for a property by location
    // The user can browse properties by type of place
    // The user can filter properties by minimum rating.
    // The user can then choose one from the returned lists to book
    // to string information of a selected property should include the name of the property, host, location, 
    //   type of place description, maximum number of guests, rating, total price without discount for the period of
    //   stay with price breakdown (i.e., price per night * number of nights), total price with discount
    //   for the period of stay with price breakdown (i.e., discounted price per night * number of
    //   nights) if discount is applied, total service fee with fee breakdown (i.e., service fee per night *
    //   number of nights), cleaning fee, and total payment.
}
