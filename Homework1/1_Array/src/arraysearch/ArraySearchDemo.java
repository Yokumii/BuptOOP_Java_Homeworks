package arraysearch;

import java.util.Arrays;

public class ArraySearchDemo {
    public static void main(String[] args) {
        // 创建并初始化测试数组
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        // 手动添加几个重复值用于演示
        arr[5] = 50;
        arr[10] = 50;
        arr[15] = 50;

        System.out.println("原始数组：" + Arrays.toString(arr));

        // 1. 使用线性搜索
        int target = 50;
        int linearResult = linearSearch(arr, target);
        System.out.println("\n线性搜索结果：");
        if (linearResult != -1) {
            System.out.println("找到目标值 " + target + " 在位置: " + linearResult);
            // 找出所有相同值的位置
            System.out.print("所有相同值的位置: ");
            findAllOccurrences(arr, target);
        } else {
            System.out.println("未找到目标值 " + target);
        }

        // 2. 使用二分搜索
        // 注意：必须先排序
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        System.out.println("\n排序后数组：" + Arrays.toString(sortedArr));

        // 使用自己实现的二分搜索
        int binaryResult = binarySearch(sortedArr, target);
        System.out.println("\n自实现二分搜索结果：");
        if (binaryResult != -1) {
            System.out.println("找到目标值 " + target + " 在位置: " + binaryResult);
        } else {
            System.out.println("未找到目标值 " + target);
        }

        // 使用Arrays.binarySearch()
        int libraryResult = Arrays.binarySearch(sortedArr, target);
        System.out.println("\nArrays.binarySearch()结果：");
        if (libraryResult >= 0) {
            System.out.println("找到目标值 " + target + " 在位置: " + libraryResult);
        } else {
            System.out.println("未找到目标值 " + target);
            // 如果要插入，应该插入的位置是 -libraryResult - 1
            System.out.println("如果要插入，应该插入的位置是: " + (-libraryResult - 1));
        }
    }

    // 线性搜索（返回第一个匹配的位置）
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 找出所有匹配的位置
    public static void findAllOccurrences(int[] arr, int target) {
        boolean first = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(i);
                first = false;
            }
        }
        System.out.println();
    }

    // 自实现二分搜索
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免整数溢出

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
