import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class EcoPointsRecyclingTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static  Map<String, HouseHold> households = new HashMap<>();

    public static void main(String[] args){

        loadHouseholdsFromFile();       // Load saved data before anything else

        boolean running = true;

        while (true){
            System.out.println("\n=== Eco-Points Recycling Tracker ===");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display Households");
            System.out.println("4. Display Household Recycling Events");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String userOption = scanner.nextLine();
            switchLoop:
            switch(userOption){
                case "1":
                    registerHousehold();
                    break;

                case "2":
                    logRecyclingEvent();
                    break;

                case "3":
                    displayHouseholds();
                    break;

                case "4":
                    displayHouseholdEvents();
                    break;

                case "5":
                    generateReports();
                    break;

                case "6":
                    saveHouseholdsToFile();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void registerHousehold(){

        System.out.print("Enter household ID (e.g. HH001): ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (!id.matches("^HH\\d{3}$")){
            System.out.println("Invalid ID format. Please use format like HH001.");
            return;

        } else if  (households.containsKey(id)){
            System.out.println("Error: Household ID already exists.");
            return;

        }else {
            System.out.println("Confirmed ID " + id);

        }

        System.out.print("Enter household name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter household address: ");
        String address = scanner.nextLine().trim();

        HouseHold household = new HouseHold(id, name, address);
        households.put(id, household);

    }
    private static void logRecyclingEvent() {

        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();

        HouseHold household = households.get(id.toUpperCase());
        if (household == null) {
            System.out.println("Error: Household ID not found.");
            return;
        }

        System.out.println("\n===== Material Type Menu =====");
        System.out.println("Enter one of the following:");
        System.out.println("pl - Plastic");
        System.out.println("g  - Glass");
        System.out.println("m  - Metal");
        System.out.println("pp - Paper");
        System.out.print("Your choice: ");

        String materialChoice = scanner.nextLine().trim().toLowerCase();
        String materialType;

        switch (materialChoice) {
            case "pl": materialType = "Plastic"; break;
            case "g": materialType = "Glass"; break;
            case "m": materialType = "Metal"; break;
            case "pp": materialType = "Paper"; break;
            default:
                System.out.println("Invalid material type selected.");
                return;
        }

        double weight = 0.0;

        while (true) {
            try {
                System.out.print("Enter weight in kilograms: ");
                weight = Double.parseDouble(scanner.nextLine());
                if (weight <= 0.0) {
                    System.out.println("Weight must be greater than zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        RecyclingEvent event = new RecyclingEvent(materialType, weight);
        //event.calculateEcoPoints();
        household.addEvent(event);


        System.out.println("\nRecycling event logged successfully!");
        System.out.println("Material: " + materialType);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Points Earned: " + event.getEcoPoints());
        System.out.println("Date: " + event.getDate());
    }

    private static void displayHouseholds(){
        if (households.isEmpty()){
            System.out.println("No households registered.");
            return;
        }
        System.out.println("\nRegistered Households:");
        for (HouseHold h : households.values()){
            System.out.println("ID: " + h.getID() +
                    ", Name: " + h.getName() +
                    ", Address: " + h.getAddress() +
                    ", Joined: " + h.getJoinDate());
        }
    }
    private static void displayHouseholdEvents(){
        System.out.print("Would you like...");
        System.out.println("\n(a). Events for individual households " +
                "\n(b) Events for the entire community");

        String display = scanner.nextLine().toLowerCase();
        switch (display){
            case "a":
                displayHouseholdEventsA();
                break;

            case "b":
                displayHouseholdEventsB();
                break;

            default:
                System.out.println("Invalid choice. Try either 'a' or 'b'");
        }
    }
    private static void displayHouseholdEventsA(){
        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();

        HouseHold household = households.get(id);
        if (household == null){
            System.out.println("Household not found.");
            return;
        }
        System.out.println("\nRecycling Events for " + household.getName() +": ");

        if (household.getEvents().isEmpty()){
            System.out.println("No events logged.");

        }else {
            for (RecyclingEvent evt : household.getEvents()){
                System.out.println(evt);
            }
            System.out.println("Total Weight: " + household.getTotalWeight() + " kg");
            System.out.println("Total Points: " + household.getTotalPoints() + " pts");

        }
    }
    private static void displayHouseholdEventsB(){
        if (households.isEmpty()){
            System.out.println("No households registered.");
            return;
        }

        boolean hasEvents = false;
        for (HouseHold house : households.values()){
            if (!house.getEvents().isEmpty()){
                hasEvents = true;
                System.out.println("\nHousehold: " + house.getName());
            }
            for (RecyclingEvent evt : house.getEvents()){
                System.out.println(evt);
            }
            System.out.println("Total Weight: " + house.getTotalWeight() + " kg");
            System.out.println("Total Points: " + house.getTotalPoints() + " pts");
        }
        if (!hasEvents) System.out.println("No recycling events logged in the community yet.");
    }
    public static void generateReports(){
        if (households.isEmpty()){
            System.out.println("No households registered.");
            return;
        }
        System.out.println("\n=== Recycling Report for(" + LocalDate.now() + ") ===");

        // Sort all participants by points in ascending order

        List<HouseHold> sortedList = new ArrayList<>(households.values());
        sortedList.sort(Comparator.comparingDouble(HouseHold::getTotalPoints).reversed());

        System.out.println("\n--- Households Sorted by Eco-Points (Ascending) ---");
        for (HouseHold hse : sortedList) {

            System.out.println("ID: " + hse.getID() +
                    " | Name: " + hse.getName() +
                    " | Total Points: " + hse.getTotalPoints());
        }

        //Sort waste products in descending order in terms of the total weight per product

        Map<String,Double> materialsTotal= new HashMap<>();

        for (HouseHold hse : households.values()){
            for (RecyclingEvent env : hse.getEvents()){
                materialsTotal.merge(env.getMaterialType(), env.getWeight(), Double::sum);
            }
        }
        List<Map.Entry<String, Double>> sortedMaterials = new ArrayList<>(materialsTotal.entrySet());
        sortedMaterials.sort((a,b) -> Double.compare(b.getValue(), a.getValue()));

        System.out.println("\n--- Material Weight Report ---");
        if (sortedMaterials.isEmpty()) {
            System.out.println("No recycling events recorded yet.");

        } else {
            for (Map.Entry<String, Double> entry : sortedMaterials) {
                System.out.printf("%-10s : %.2f kg%n", entry.getKey(), entry.getValue());
            }
        }

        // ------------------------------
        // Calculate total community recycling weight
        // ------------------------------

        double totalWeight = 0.0;
        // Loop through all households to sum up their total weights

        for (HouseHold hses : households.values()){
            totalWeight += hses.getTotalWeight();

        }
        System.out.println("Total Community Recycling Weight: " + totalWeight + " kg");

    }
    private static void saveHouseholdsToFile(){
        try {
            FileOutputStream fileOut = new  FileOutputStream("households.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(households);
            out.close();
            fileOut.close();

            System.out.println("Household data saved successfully to households.txt");

        }catch (IOException e){
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("Unchecked")      //keeps the compiler quiet when casting the deserialized object.

    private static void  loadHouseholdsFromFile(){
        File file = new File("households.txt");

        if (file.exists()){
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream in = new ObjectInputStream(fileIn)){

                households = (Map<String, HouseHold>) in.readObject();
                System.out.println("Household data loaded successfully.");

            }catch (IOException | ClassNotFoundException e){
                System.out.println("Error loading household data: " + e.getMessage());
                households = new HashMap<>();
            }

        }else {
            households = new HashMap<>();
            System.out.println("No existing household data found. Starting fresh.");
        }


    }
}
