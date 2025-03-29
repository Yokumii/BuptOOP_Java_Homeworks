// 测试类
public class DisplayTest {
    public static void main(String[] args) {
        // 创建接口数组
        Displayable[] displays = new Displayable[5];

        // 创建不同类型的对象并存入数组
        displays[0] = new Notice("明天下午3点开会");
        displays[1] = new Car(80);
        displays[2] = new Advertisement("特价促销！全场8折！");
        displays[3] = new Notice("请按时提交作业");
        displays[4] = new Car(65);

        // 通过接口数组调用display方法
        System.out.println("=== 显示所有信息 ===");
        for (int i = 0; i < displays.length; i++) {
            System.out.print("第" + (i+1) + "条信息: ");
            displays[i].display();
        }

        // 统计各类型对象数量
        System.out.println("\n=== 统计信息 ===");
        int noticeCount = 0;
        int carCount = 0;
        int adCount = 0;

        for (Displayable d : displays) {
            if (d instanceof Notice) {
                noticeCount++;
            } else if (d instanceof Car) {
                carCount++;
            } else if (d instanceof Advertisement) {
                adCount++;
            }
        }

        System.out.println("通知数量: " + noticeCount);
        System.out.println("汽车数量: " + carCount);
        System.out.println("广告数量: " + adCount);

        // 演示多态性
        System.out.println("\n=== 类型判断演示 ===");
        for (Displayable d : displays) {
            System.out.print("对象类型: " + d.getClass().getSimpleName() + " | ");
            d.display();
        }
    }
}