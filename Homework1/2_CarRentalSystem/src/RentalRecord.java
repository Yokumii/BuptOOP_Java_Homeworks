public class RentalRecord {
    private Vehicle vehicle;
    private int days;
    
    public RentalRecord(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public int getDays() {
        return days;
    }
    
    public int calculatePrice() {
        return vehicle.getRent() * days;
    }
} 