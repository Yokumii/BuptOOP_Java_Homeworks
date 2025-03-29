public abstract class Vehicle {
    protected String name;    // 车名
    protected int rent;       // 日租金
    protected int number;     // 车辆编号
    
    public Vehicle(String name, int rent, int number) {
        this.name = name;
        this.rent = rent;
        this.number = number;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRent() {
        return rent;
    }
    
    public int getNumber() {
        return number;
    }
    
    // 抽象方法，用于显示车辆信息
    public abstract String getInfo();
}
