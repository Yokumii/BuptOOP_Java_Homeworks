// 载货车辆
public class CargoVehicle extends Vehicle {
    private int cargoCapacity;  // 载货容量（吨）
    
    public CargoVehicle(String name, int rent, int number, int cargoCapacity) {
        super(name, rent, number);
        this.cargoCapacity = cargoCapacity;
    }
    
    @Override
    public String getInfo() {
        return String.format("序号：%d  汽车名称:%s 租金%d/天 容量：最多载货%d吨", 
            number, name, rent, cargoCapacity);
    }
} 