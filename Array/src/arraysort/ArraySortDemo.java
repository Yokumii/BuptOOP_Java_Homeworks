package arraysort;

import java.util.Arrays;

public class ArraySortDemo {
    public static void main(String[] args) {
        // 创建三个相同内容的数组用于比较不同排序方法
        int[] arr1 = generateRandomArray(200);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);

        System.out.println("原始数组：" + Arrays.toString(arr1));

        // 1. 使用Arrays.sort()排序
        long startTime = System.nanoTime();
        Arrays.sort(arr1);
        long endTime = System.nanoTime();
        System.out.println("\nArrays.sort()排序后：" + Arrays.toString(arr1));
        System.out.println("Arrays.sort()耗时：" + (endTime - startTime) + "纳秒");

        // 2. 使用选择排序
        startTime = System.nanoTime();
        selectionSort(arr2);
        endTime = System.nanoTime();
        System.out.println("\n选择排序后：" + Arrays.toString(arr2));
        System.out.println("选择排序耗时：" + (endTime - startTime) + "纳秒");

        // 3. 使用冒泡排序
        startTime = System.nanoTime();
        bubbleSort(arr3);
        endTime = System.nanoTime();
        System.out.println("\n冒泡排序后：" + Arrays.toString(arr3));
        System.out.println("冒泡排序耗时：" + (endTime - startTime) + "纳秒");
    }

    // 生成随机数组
    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 200); // 生成0-99的随机数
        }
        return arr;
    }

    // 选择排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到从i到末尾最小的元素的下标
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 将找到的最小元素和第i个元素交换位置
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // 从第一个元素开始，相邻两个元素比较
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 如果没有发生交换，说明数组已经有序，提前退出
            if (!swapped) {
                break;
            }
        }
    }
}
