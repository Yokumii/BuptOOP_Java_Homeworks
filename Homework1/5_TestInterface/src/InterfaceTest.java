// 测试类
public class InterfaceTest implements TestInterface {
    @Override
    public void normalMethod() {
        System.out.println("实现普通方法");
    }

    @Override
    public void publicMethod() {
        System.out.println("实现public方法");
    }

    public static void main(String[] args) {
        // 1. 测试接口属性的static特性
        System.out.println("=== 测试接口属性的static特性 ===");
        // 可以通过接口名直接访问属性，证明属性是static的
        System.out.println("NORMAL = " + TestInterface.NORMAL);
        System.out.println("PUBLIC = " + TestInterface.PUBLIC);
        System.out.println("STATIC = " + TestInterface.STATIC);
        System.out.println("FINAL = " + TestInterface.FINAL);

        // 2. 测试接口属性的final特性
        System.out.println("\n=== 测试接口属性的final特性 ===");
        try {
            // 尝试修改接口属性，会导致编译错误
            // TestInterface.NORMAL = 100;  // 取消注释将导致编译错误
            System.out.println("无法修改接口属性，证明属性是final的");
        } catch (Exception e) {
            System.out.println("修改接口属性失败：" + e.getMessage());
        }

        // 3. 测试接口方法的public特性
        System.out.println("\n=== 测试接口方法的public特性 ===");
        InterfaceTest test = new InterfaceTest();

        // 所有方法都可以被调用，证明它们都是public的
        test.normalMethod();
        test.publicMethod();
        test.defaultMethod();  // 调用默认方法
        TestInterface.staticMethod();  // 调用静态方法

        // 4. 通过反射验证属性修饰符
        System.out.println("\n=== 通过反射验证属性修饰符 ===");
        try {
            java.lang.reflect.Field[] fields = TestInterface.class.getFields();
            for (java.lang.reflect.Field field : fields) {
                int modifiers = field.getModifiers();
                System.out.printf("属性 %s 的修饰符: public=%b, static=%b, final=%b%n",
                        field.getName(),
                        java.lang.reflect.Modifier.isPublic(modifiers),
                        java.lang.reflect.Modifier.isStatic(modifiers),
                        java.lang.reflect.Modifier.isFinal(modifiers));
            }
        } catch (Exception e) {
            System.out.println("反射检查失败：" + e.getMessage());
        }

        // 5. 通过反射验证方法修饰符
        System.out.println("\n=== 通过反射验证方法修饰符 ===");
        try {
            java.lang.reflect.Method[] methods = TestInterface.class.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                int modifiers = method.getModifiers();
                System.out.printf("方法 %s 的修饰符: public=%b, abstract=%b%n",
                        method.getName(),
                        java.lang.reflect.Modifier.isPublic(modifiers),
                        java.lang.reflect.Modifier.isAbstract(modifiers));
            }
        } catch (Exception e) {
            System.out.println("反射检查失败：" + e.getMessage());
        }
    }
}