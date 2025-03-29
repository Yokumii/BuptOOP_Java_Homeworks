public class InterfaceMethodTest {
    public static void main(String[] args) {
        System.out.println("=== 测试接口变量调用非接口方法 ===");

        // 1. 直接通过类引用调用（正常方式）
        System.out.println("\n1. 通过类引用调用：");
        AdvancedDisplay ad1 = new AdvancedDisplay("测试内容1", 1);
        ad1.display();        // 接口方法
        ad1.showDetails();    // 类自己的方法
        ad1.setPriority(2);   // 类自己的方法

        // 2. 通过接口变量调用（需要类型转换）
        System.out.println("\n2. 通过接口变量调用（使用强制类型转换）：");
        Displayable d1 = new AdvancedDisplay("测试内容2", 3);
        d1.display();  // 可以直接调用接口方法

        // 要调用非接口方法，需要强制类型转换
        AdvancedDisplay converted = (AdvancedDisplay) d1;
        converted.showDetails();
        converted.setPriority(4);

        // 3. 使用instanceof进行安全的类型转换
        System.out.println("\n3. 使用instanceof进行安全的类型转换：");
        Displayable d2 = new AdvancedDisplay("测试内容3", 5);
        if (d2 instanceof AdvancedDisplay) {
            AdvancedDisplay ad2 = (AdvancedDisplay) d2;
            ad2.showDetails();
            ad2.setPriority(6);
        }

        // 4. 通过方法返回特定类型（工厂方法模式）
        System.out.println("\n4. 通过工厂方法获取特定类型：");
        Displayable d3 = createAdvancedDisplay("测试内容4", 7);
        AdvancedDisplay ad3 = getAdvancedDisplay(d3);
        if (ad3 != null) {
            ad3.showDetails();
            ad3.setPriority(8);
        }

        // 5. 使用接口扩展
        System.out.println("\n5. 使用扩展接口：");
        ExtendedDisplayable ed = new CompleteDisplay("测试内容5", 9);
        ed.display();
        ed.showDetails();  // 可以直接调用扩展接口的方法

        // 6. 演示错误的类型转换
        System.out.println("\n6. 演示错误的类型转换：");
        try {
            Displayable wrongDisplay = new WrongDisplay();
            AdvancedDisplay wrongConverted = (AdvancedDisplay) wrongDisplay;  // 将抛出异常
        } catch (ClassCastException e) {
            System.out.println("类型转换错误：" + e.getMessage());
        }
    }

    // 工厂方法
    private static Displayable createAdvancedDisplay(String content, int priority) {
        return new AdvancedDisplay(content, priority);
    }

    // 类型转换辅助方法
    private static AdvancedDisplay getAdvancedDisplay(Displayable d) {
        return (d instanceof AdvancedDisplay) ? (AdvancedDisplay) d : null;
    }
}

// 扩展的接口
interface ExtendedDisplayable extends Displayable {
    void showDetails();
}

// 完整实现类
class CompleteDisplay implements ExtendedDisplayable {
    private String content;
    private int priority;

    public CompleteDisplay(String content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    @Override
    public void display() {
        System.out.println("显示内容：" + content);
    }

    @Override
    public void showDetails() {
        System.out.println("详细信息 - 内容: " + content + ", 优先级: " + priority);
    }
}

// 用于演示错误类型转换的类
class WrongDisplay implements Displayable {
    @Override
    public void display() {
        System.out.println("错误显示示例");
    }
}
