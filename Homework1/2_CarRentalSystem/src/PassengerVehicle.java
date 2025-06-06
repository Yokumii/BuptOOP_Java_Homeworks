// 载人车辆
public class PassengerVehicle extends Vehicle {
    private int passengerCapacity;  // 载人容量
    
    public PassengerVehicle(String name, int rent, int number, int passengerCapacity) {
        super(name, rent, number);
        this.passengerCapacity = passengerCapacity;
    }
    
    @Override
    public String getInfo() {
        return String.format("序号：%d  汽车名称:%s 租金%d/天 容量：最多载人%d人", 
            number, name, rent, passengerCapacity);
    }
} 