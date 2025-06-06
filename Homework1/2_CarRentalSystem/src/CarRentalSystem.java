import java.util.*;

public class CarRentalSystem {
    private List<Vehicle> vehicles;
    private List<RentalRecord> rentalRecords;
    
    public CarRentalSystem() {
        vehicles = new ArrayList<>();
        rentalRecords = new ArrayList<>();
        initializeVehicles();
    }
    
    private void initializeVehicles() {
        // 初始化车辆列表
        vehicles.add(new PassengerVehicle("奥迪A4", 500, 1, 4));
        vehicles.add(new PassengerVehicle("马自达6", 400, 2, 4));
        vehicles.add(new DualPurposeVehicle("皮卡雪6", 450, 3, 4, 2));
        vehicles.add(new PassengerVehicle("金龙", 800, 4, 20));
        vehicles.add(new CargoVehicle("松花江", 400, 5, 4));
        vehicles.add(new CargoVehicle("依维柯", 1000, 6, 20));
    }
    
    public void displayVehicles() {
        System.out.println("您可租车的类型及其价目表");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getInfo());
        }
    }
    
    public void processRental() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("请输入您要租汽车的数量：");
        int count = scanner.nextInt();
        
        System.out.println("请输入您要租车的序号和天数");
        // 清空之前的租车记录
        rentalRecords.clear();
        
        // 收集租车信息
        for (int i = 0; i < count; i++) {
            int number = scanner.nextInt();
            int days = scanner.nextInt();
            
            Vehicle vehicle = findVehicle(number);
            if (vehicle != null) {
                rentalRecords.add(new RentalRecord(vehicle, days));
            } else {
                System.out.println("警告：未找到序号为 " + number + " 的车辆");
            }
        }
        
        // 显示账单
        displayBill();
    }
    
    private void displayBill() {
        if (rentalRecords.isEmpty()) {
            System.out.println("没有租车记录");
            return;
        }
        
        System.out.println("\n您的账单：");
        int totalPrice = 0;
        
        // 显示每条租车记录
        for (RentalRecord record : rentalRecords) {
            Vehicle vehicle = record.getVehicle();
            int days = record.getDays();
            System.out.printf("%s   %d天\n", vehicle.getName(), days);
            totalPrice += vehicle.getRent() * days;
        }
        
        // 显示总价
        System.out.println("总价格：" + totalPrice + "元");
    }
    
    private Vehicle findVehicle(int number) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getNumber() == number) {
                return vehicle;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        
        System.out.println("欢迎使用租车系统");
        System.out.println("你是否要租车:1是 0否");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            rentalSystem.displayVehicles();
            rentalSystem.processRental();
        } else {
            System.out.println("欢迎下次再来");
        }
        
        scanner.close();
    }
}
