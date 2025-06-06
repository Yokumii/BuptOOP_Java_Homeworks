package arraysort;

import java.util.Arrays;
import java.util.Random;

public class Array2DSortDemo {
    public static void main(String[] args) {
        // 1. 创建并初始化10x10的二维数组
        int[][] array2D = new int[10][10];
        initializeArray(array2D);

        System.out.println("原始二维数组：");
        printArray2D(array2D);

        // 2. 使用选择排序
        int[][] array2DCopy1 = deepCopy2DArray(array2D);
        selectionSort2D(array2DCopy1);
        System.out.println("\n选择排序后的二维数组：");
        printArray2D(array2DCopy1);

        // 3. 使用Arrays.sort（转换为一维数组）
        int[][] array2DCopy2 = deepCopy2DArray(array2D);
        sortUsing1DArray(array2DCopy2);
        System.out.println("\n使用一维数组排序后的二维数组：");
        printArray2D(array2DCopy2);

        // 4. 直接对每行使用Arrays.sort
        int[][] array2DCopy3 = deepCopy2DArray(array2D);
        sortByRow(array2DCopy3);
        System.out.println("\n每行单独排序后的二维数组：");
        printArray2D(array2DCopy3);
    }

    // 初始化二维数组
    public static void initializeArray(int[][] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(101); // 0-100的随机数
            }
        }
    }

    // 深拷贝二维数组
    public static int[][] deepCopy2DArray(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    // 打印二维数组
    public static void printArray2D(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
    }

    // 方法1：选择排序实现
    public static void selectionSort2D(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int totalElements = rows * cols;

        for (int i = 0; i < totalElements - 1; i++) {
            int minIndex = i;
            int minValue = getElement(arr, i);

            // 寻找最小元素
            for (int j = i + 1; j < totalElements; j++) {
                int currentValue = getElement(arr, j);
                if (currentValue < minValue) {
                    minValue = currentValue;
                    minIndex = j;
                }
            }

            // 交换元素
            if (minIndex != i) {
                swapElements(arr, i, minIndex);
            }
        }
    }

    // 获取展平后位置的元素
    private static int getElement(int[][] arr, int flatIndex) {
        int row = flatIndex / arr[0].length;
        int col = flatIndex % arr[0].length;
        return arr[row][col];
    }

    // 交换两个位置的元素
    private static void swapElements(int[][] arr, int flatIndex1, int flatIndex2) {
        int row1 = flatIndex1 / arr[0].length;
        int col1 = flatIndex1 % arr[0].length;
        int row2 = flatIndex2 / arr[0].length;
        int col2 = flatIndex2 % arr[0].length;

        int temp = arr[row1][col1];
        arr[row1][col1] = arr[row2][col2];
        arr[row2][col2] = temp;
    }

    // 方法2：使用一维数组排序
    public static void sortUsing1DArray(int[][] arr) {
        // 1. 创建一维数组
        int rows = arr.length;
        int cols = arr[0].length;
        int[] flatArray = new int[rows * cols];

        // 2. 复制到一维数组
        for (int i = 0; i < rows; i++) {
            System.arraycopy(arr[i], 0, flatArray, i * cols, cols);
        }

        // 3. 排序一维数组
        Arrays.sort(flatArray);

        // 4. 复制回二维数组
        for (int i = 0; i < rows; i++) {
            System.arraycopy(flatArray, i * cols, arr[i], 0, cols);
        }
    }

    // 方法3：直接对每行排序
    public static void sortByRow(int[][] arr) {
        for (int[] row : arr) {
            Arrays.sort(row);
        }
    }
}
