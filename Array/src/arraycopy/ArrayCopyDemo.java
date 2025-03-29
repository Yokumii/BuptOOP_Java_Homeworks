package arraycopy;

import java.util.Arrays;

public class ArrayCopyDemo {
    public static void main(String[] args) {
        // 原始数组
        int[] original = {1, 2, 3, 4, 5};

        // 1. 使用System.arraycopy
        int[] target1 = new int[5];
        System.arraycopy(original, 0, target1, 0, original.length);
        System.out.println("System.arraycopy结果：" + Arrays.toString(target1));

        // 2. 使用Arrays.copyOf
        int[] target2 = Arrays.copyOf(original, original.length);
        System.out.println("Arrays.copyOf结果：" + Arrays.toString(target2));

        // 3. 使用Arrays.copyOfRange
        // 注意：toIndex是开区间，即复制范围是[fromIndex, toIndex)
        int[] target3 = Arrays.copyOfRange(original, 1, 4);
        System.out.println("Arrays.copyOfRange结果(1-4)：" + Arrays.toString(target3));

        // 4. 手动实现数组复制
        int[] target4 = manualArrayCopy(original);
        System.out.println("手动复制结果：" + Arrays.toString(target4));
    }

    // 手动实现数组复制
    public static int[] manualArrayCopy(int[] source) {
        int[] target = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }
}
