package arraycompare;

import java.util.Arrays;

public class ArrayCompareDemo {
    public static void main(String[] args) {
        // 创建测试数组
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 4, 6};
        int[] arr4 = {1, 2, 3, 4};
        int[] arr5 = {5, 4, 3, 2, 1};

        System.out.println("=== 使用自定义方法比较 ===");
        // 测试相同数组
        System.out.println("arr1 和 arr2 比较结果: " + compareArrays(arr1, arr2));
        // 测试最后一个元素不同的数组
        System.out.println("arr1 和 arr3 比较结果: " + compareArrays(arr1, arr3));
        // 测试长度不同的数组
        System.out.println("arr1 和 arr4 比较结果: " + compareArrays(arr1, arr4));
        // 测试元素顺序不同的数组
        System.out.println("arr1 和 arr5 比较结果: " + compareArrays(arr1, arr5));

        System.out.println("\n=== 使用Arrays.equals()比较 ===");
        System.out.println("arr1 和 arr2 比较结果: " + Arrays.equals(arr1, arr2));
        System.out.println("arr1 和 arr3 比较结果: " + Arrays.equals(arr1, arr3));
        System.out.println("arr1 和 arr4 比较结果: " + Arrays.equals(arr1, arr4));
        System.out.println("arr1 和 arr5 比较结果: " + Arrays.equals(arr1, arr5));

        // 测试多维数组比较
        int[][] multiArr1 = {{1, 2}, {3, 4}};
        int[][] multiArr2 = {{1, 2}, {3, 4}};
        int[][] multiArr3 = {{1, 2}, {3, 5}};

        System.out.println("\n=== 多维数组比较 ===");
        System.out.println("使用自定义方法比较多维数组:");
        System.out.println("multiArr1 和 multiArr2 比较结果: " + compareMultiArrays(multiArr1, multiArr2));
        System.out.println("multiArr1 和 multiArr3 比较结果: " + compareMultiArrays(multiArr1, multiArr3));

        System.out.println("\n使用Arrays.deepEquals()比较多维数组:");
        System.out.println("multiArr1 和 multiArr2 比较结果: " + Arrays.deepEquals(multiArr1, multiArr2));
        System.out.println("multiArr1 和 multiArr3 比较结果: " + Arrays.deepEquals(multiArr1, multiArr3));
    }

    // 自定义比较一维数组的方法
    public static boolean compareArrays(int[] arr1, int[] arr2) {
        // 1. 检查是否为null
        if (arr1 == null || arr2 == null) {
            return arr1 == arr2;
        }

        // 2. 检查长度是否相同
        if (arr1.length != arr2.length) {
            return false;
        }

        // 3. 逐个比较元素
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    // 自定义比较二维数组的方法
    public static boolean compareMultiArrays(int[][] arr1, int[][] arr2) {
        // 1. 检查是否为null
        if (arr1 == null || arr2 == null) {
            return arr1 == arr2;
        }

        // 2. 检查外层数组长度
        if (arr1.length != arr2.length) {
            return false;
        }

        // 3. 逐个比较内层数组
        for (int i = 0; i < arr1.length; i++) {
            if (!compareArrays(arr1[i], arr2[i])) {
                return false;
            }
        }

        return true;
    }
}
