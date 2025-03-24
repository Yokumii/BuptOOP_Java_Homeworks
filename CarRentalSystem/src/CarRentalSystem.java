import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

    List<Vehicle> vehicles;

    void DisplayVehicles() {

    }

    public static void main(String[] args) {
        CarRentalSystem rs = new CarRentalSystem();

        System.out.println("欢迎使用租车系统");
        System.out.println("你是否要租车:1是 0否");
        Scanner in = new Scanner(System.in);
        if (in.nextInt() == 1) {
            System.out.println("您可租车的类型及其价目表");
        }
        else {
            System.out.println("欢迎下次再来");
            return;
        }
    }
}
