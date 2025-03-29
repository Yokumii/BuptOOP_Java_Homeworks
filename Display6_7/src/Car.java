// 汽车类
public class Car implements Displayable {
    private int fuelLevel;  // 油量

    public Car(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public void display() {
        System.out.println("汽车油量：" + fuelLevel);
    }
}