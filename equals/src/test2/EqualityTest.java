package test2;

import test1.C1;
import test1.C2;

public class EqualityTest {
    public static void main(String[] args) {
        // 测试第一种实现
        System.out.println("=== 测试标准equals实现 ===");
        test1.C1 c1_1 = new test1.C1(1, 2);
        test1.C1 c1_2 = new test1.C1(1, 2);
        test1.C2 c2_1 = new test1.C2(1, 2);

        System.out.println("同类型对象相等测试: " + c1_1.equals(c1_2));  // true
        System.out.println("不同类型对象相等测试: " + c1_1.equals(c2_1));  // false

        // 测试第二种实现
        System.out.println("\n=== 测试修改后的equals实现 ===");
        test1.C1 c1_3 = new C1(1, 2);
        test1.C2 c2_2 = new test1.C2(1, 2);
        test1.C2 c2_3 = new C2(1, 3);

        System.out.println("不同类型但值相等测试: " + c1_3.equals(c2_2));  // true
        System.out.println("不同类型且值不等测试: " + c1_3.equals(c2_3));  // false
    }
}
