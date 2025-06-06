package arrayfill;

import java.util.Arrays;

public class ArrayFillDemo {
    public static void main(String[] args) {
        // 1. 基本类型数组的填充
        int[] intArray = new int[10];

        // 使用Arrays.fill填充整个数组
        Arrays.fill(intArray, 8);
        System.out.println("填充整个数组为8：" + Arrays.toString(intArray));

        // 填充数组的指定范围
        Arrays.fill(intArray, 3, 7, 5);  // 填充索引[3,7)的元素为5
        System.out.println("部分填充为5：" + Arrays.toString(intArray));

        // 2. 字符数组填充
        char[] charArray = new char[10];
        Arrays.fill(charArray, '*');
        System.out.println("字符数组填充：" + Arrays.toString(charArray));

        // 3. 对象数组填充
        String[] strArray = new String[5];
        Arrays.fill(strArray, "Hello");
        System.out.println("字符串数组填充：" + Arrays.toString(strArray));

        // 4. 二维数组填充
        int[][] matrix = new int[3][4];
        fillMatrix(matrix, 1);
        System.out.println("二维数组填充：");
        printMatrix(matrix);

        // 5. 不同类型数组的填充示例
        System.out.println("\n不同类型数组填充示例：");

        // 布尔数组
        boolean[] boolArray = new boolean[5];
        Arrays.fill(boolArray, true);
        System.out.println("布尔数组：" + Arrays.toString(boolArray));

        // 双精度数组
        double[] doubleArray = new double[5];
        Arrays.fill(doubleArray, 3.14);
        System.out.println("双精度数组：" + Arrays.toString(doubleArray));

        // 6. 自定义对象数组填充
        Student[] students = new Student[3];
        Arrays.fill(students, new Student("默认学生", 18));
        System.out.println("学生数组填充：" + Arrays.toString(students));

        // 7. 演示填充的注意事项
        demonstrateFillCaveats();
    }

    // 自定义二维数组填充方法
    public static void fillMatrix(int[][] matrix, int value) {
        for (int[] row : matrix) {
            Arrays.fill(row, value);
        }
    }

    // 打印二维数组的辅助方法
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // 演示填充时的注意事项
    public static void demonstrateFillCaveats() {
        System.out.println("\n=== 填充注意事项演示 ===");

        // 1. 对象引用的注意事项
        Student[] students = new Student[3];
        Student student = new Student("测试学生", 20);
        Arrays.fill(students, student);

        // 修改student对象
        student.setAge(21);
        System.out.println("修改后的学生数组（所有引用指向同一对象）：");
        System.out.println(Arrays.toString(students));

        // 2. 正确的对象数组填充方法
        Student[] studentsCorrect = new Student[3];
        for (int i = 0; i < studentsCorrect.length; i++) {
            studentsCorrect[i] = new Student("学生" + (i+1), 20);
        }
        System.out.println("正确的对象数组填充：");
        System.out.println(Arrays.toString(studentsCorrect));
    }
}

// 用于演示的Student类
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
