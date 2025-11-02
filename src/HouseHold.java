import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseHold implements Serializable{

    private String ID;
    private String name;
    private String address;
    private LocalDate joinDate;
    private List<RecyclingEvent> events = new ArrayList<>();
    private double totalPoints;

    public HouseHold(String ID, String name, String address,
                     LocalDate joinDate, List<RecyclingEvent> events, double totalPoints){

        this.ID = ID;
        this.name = name;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.events = new ArrayList<>(); //Task 2
        this.totalPoints = 0.0;
    }

    public HouseHold(String id, String name, String address) {
        this.ID = id;
        this.name = name;
        this.address = address;
    }

    public String getID() {
        return ID;

    }
    public void setID(String ID) {
        this.ID = ID;

    }
    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;

    }
    public String getAddress() {
        return address;

    }
    public void setAddress(String address) {
        this.address = address;

    }
    public LocalDate getJoinDate() {
        return joinDate;

    }
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;

    }
    public List<RecyclingEvent> getEvents() {
        return events;

    }
    public double getTotalPoints() {
        return totalPoints;

    }
    public void addEvent(RecyclingEvent event){
        this.events.add(event);
        this.totalPoints +=event.getEcoPoints();

    }
    public double getTotalWeight(){
        double total = 0.0;

        for (RecyclingEvent event : events){
            total += event.getWeight();
        }
        return total;
    }

}

