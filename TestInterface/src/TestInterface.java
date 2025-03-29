// 定义一个测试接口
interface TestInterface {
    // 以不同方式声明属性
    int NORMAL = 1;                    // 普通声明
    public int PUBLIC = 2;             // 使用public修饰
    static int STATIC = 3;             // 使用static修饰
    final int FINAL = 4;               // 使用final修饰
    public static int PUBLIC_STATIC = 5;    // 使用public static修饰
    public final int PUBLIC_FINAL = 6;      // 使用public final修饰
    static final int STATIC_FINAL = 7;      // 使用static final修饰
    public static final int ALL = 8;        // 使用所有修饰符

    // 以不同方式声明方法
    void normalMethod();               // 普通方法声明
    public void publicMethod();        // 使用public声明

    // 默认方法
    default void defaultMethod() {
        System.out.println("这是默认方法");
    }

    // 静态方法
    static void staticMethod() {
        System.out.println("这是静态方法");
    }
}
