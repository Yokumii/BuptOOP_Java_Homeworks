// 既载人又载货的车辆
public class DualPurposeVehicle extends Vehicle {
    private int passengerCapacity;  // 载人容量
    private int cargoCapacity;      // 载货容量（吨）
    
    public DualPurposeVehicle(String name, int rent, int number, 
                             int passengerCapacity, int cargoCapacity) {
        super(name, rent, number);
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
    }
    
    @Override
    public String getInfo() {
        return String.format("序号：%d  汽车名称:%s 租金%d/天 容量：最多载人%d人，最多载货%d吨", 
            number, name, rent, passengerCapacity, cargoCapacity);
    }
} 