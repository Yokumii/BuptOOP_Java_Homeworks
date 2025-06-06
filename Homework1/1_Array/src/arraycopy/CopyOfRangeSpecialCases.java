package arraycopy;

import java.util.Arrays;

public class CopyOfRangeSpecialCases {
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};

        // 1. to超出数组长度
        int[] case1 = Arrays.copyOfRange(original, 0, 7);
        System.out.println("超出长度：" + Arrays.toString(case1)); // [1, 2, 3, 4, 5, 0, 0]

        // 2. from和to相等
        int[] case2 = Arrays.copyOfRange(original, 2, 2);
        System.out.println("相等索引：" + Arrays.toString(case2)); // []

        // 3. 负数索引会抛出异常
        try {
            int[] case3 = Arrays.copyOfRange(original, -1, 3);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("负数索引会抛出异常");
        }
    }
}
