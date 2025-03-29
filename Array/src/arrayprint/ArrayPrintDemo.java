package arrayprint;

import java.util.Arrays;

public class ArrayPrintDemo {
    public static void main(String[] args) {
        // 创建测试用的各种类型数组
        int[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Hello", "World", "Java"};
        double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};

        // 1. 使用Arrays.toString()方法
        System.out.println("=== 使用Arrays.toString()方法 ===");
        System.out.println("整型数组: " + Arrays.toString(intArray));
        System.out.println("字符串数组: " + Arrays.toString(strArray));
        System.out.println("浮点数组: " + Arrays.toString(doubleArray));
        System.out.println("字符数组: " + Arrays.toString(charArray));

        // 2. 使用for循环手动打印
        System.out.println("\n=== 使用for循环手动打印 ===");

        // 打印整型数组
        System.out.print("整型数组: [");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i]);
            if (i < intArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // 打印字符串数组
        System.out.print("字符串数组: [");
        for (int i = 0; i < strArray.length; i++) {
            System.out.print(strArray[i]);
            if (i < strArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // 3. 使用增强型for循环（foreach）
        System.out.println("\n=== 使用增强型for循环 ===");
        System.out.print("浮点数组: [");
        boolean first = true;
        for (double num : doubleArray) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(num);
            first = false;
        }
        System.out.println("]");

        // 4. 自定义数组打印方法
        System.out.println("\n=== 使用自定义打印方法 ===");
        printArray(charArray);
    }

    // 自定义通用数组打印方法
    public static <T> void printArray(T[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println("数组内容: " + sb.toString());
    }

    // 为基本类型数组重载的方法
    public static void printArray(int[] array) {
        System.out.println("数组内容: " + Arrays.toString(array));
    }

    public static void printArray(char[] array) {
        System.out.println("数组内容: " + Arrays.toString(array));
    }

    public static void printArray(double[] array) {
        System.out.println("数组内容: " + Arrays.toString(array));
    }
}
