import java.io.Serializable;
import java.time.LocalDate;

public class RecyclingEvent implements Serializable{

    private String materialType;
    private double weight; //in kg
    private LocalDate date;
    private double ecoPoints;

    public RecyclingEvent(String materialType, double weight, LocalDate date, double ecoPoints){
        this.materialType = materialType;
        this.weight = weight;
        this.date = LocalDate.now();
        this.ecoPoints = ecoPoints;

    }

    public RecyclingEvent(String materialType, double weight) {
        this.weight = weight;
        this.materialType = materialType;
        this.date = LocalDate.now();
        this.ecoPoints = calculateEcoPoints();
    }

    public double calculateEcoPoints(){
        switch (materialType.toLowerCase()){
            case "plastic":
                return weight * 25.0;
            case "glass":
                return weight * 15.5;
            case "metal":
                return weight * 30.0;
            case "paper":
                return weight * 10.0;
            default:
                return 0.0; // unrecognized material
        }
    }

    public String getMaterialType() {
        return materialType;

    }

    public double getWeight() {
        return weight;

    }
    public LocalDate getDate() {
        return date;

    }
    public double getEcoPoints() {
        return ecoPoints;

    }
    public void setWeight(double weight) {
        this.weight = weight;

    }
    public void setDate(LocalDate date) {
        this.date = date;

    }
    public void setMaterialType(String materialType) {
        this.materialType = materialType;

    }
    public void setEcoPoints(double ecoPoints) {
        this.ecoPoints = ecoPoints;

    }

    @Override
    public String toString() {
        return  "Date: " + this.date +
                "\nMaterial Type: " + this.materialType +
                "\nWeight: " + this.weight +
                "\nEco points: " + this.ecoPoints;

    }
}
