# Melbnb – Java Console Booking Application

This CLI Java application lets users search, browse, filter, and book Melbourne homestays with robust input validation and visually pleasing aesthetics.

## Key Features
- **Property search:** Search by location , browse by type, or filter by minimum rating.(case-insensitive)
- **Booking:** Collects check-in/checkout dates (dd/MM/yyyy), shows full price breakdown including discounts when eligable, confirms choices and collects guest details. Shows  confirmation messages with details.
- **Validation & exceptions:** Includes input parsing for numbers/dates/emails, re-prompts on invalid input, and specific exceptions for all input types and senarios.
- **File I/O:** Automatically loads property data from `Melbnb.csv` at startup.
- **Output formatting:** All currency shown with two decimal places and aligned, fixed width labels for readability.

## Project Structure
- **`Property.java`** – Property data and formatted display.
- **`PropertyDatabase.java`** – Loads CSV and implements `Searchable<Property>` creates and handles Property object List.
- **`Searchable.java`** – Generic search interface.
- **`Customer.java`** – Customer details used during booking.
- **`Booking.java`** – Reservation details (customer, dates, guests, totals, property).
- **`CostCalculations.java`** – Pricing/discount logic and printable breakdown also updates pricing fields of other classes.
- **`CLI.java`** – Menu, input validation, and overall program flow cointains or calls all visual elements.
- **`Main.java`** – Entry point to program.

## How to Run
1. **Prerequisite:** Java 8+ installed in prefered IDE or code environment. Place `Melbnb.csv` in the project’s working directory.
2. **Open terminal in program directory path then Compile & run:**
   
   javac *.java
   java Main

## References


- [Oracle-Class String-replaceAll](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#replaceAll-java.lang.String-java.lang.String- )
- [W3schools-Java String format() Method](https://www.w3schools.com/java/ref_string_format.asp)
- [W3schools-What is a Regular Expression](https://www.w3schools.com/java/java_regex.asp) 
- [Stackoverflow-Align all the strings in proper way using String.format java](https://stackoverflow.com/questions/58150457/align-all-the-strings-in-proper-way-using-string-format-java)
- [Stackoverflow-Java regular expression OR operator](https://stackoverflow.com/questions/2031805/java-regular-expression-or-operator )
- [Stckoverflow-complex regular expression in Java](https://stackoverflow.com/questions/31713523/complex-regular-expression-in-java )
- [Codemia-Calculate days between two Dates in Java 8](https://codemia.io/knowledge-hub/path/calculate_days_between_two_dates_in_java_8)
- [Digitalocean-Master Java Date Formatting: SimpleDateFormat & DateFormat Guide](https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format )
- [GithubGist-Terminal emojis.](https://gist.github.com/nicolasdao/8f0220d050f585be1b56cc615ef6c12e)