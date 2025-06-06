import java.io.FileInputStream;
import java.io.IOException;

public class SafeExceptionDemo {
    public static void main(String[] args) {
        // 设置全局未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("捕获到未处理的异常：");
            GlobalExceptionHandler.handleException(throwable);
        });

        try {
            // 测试各种可能的异常情况
            testVariousExceptions();
        } catch (Throwable t) {
            // 捕获所有可能的异常和错误
            GlobalExceptionHandler.handleException(t);
        }
    }

    private static void testVariousExceptions() {
        try {
            // 1. 测试算术异常
            testArithmeticException();

            // 2. 测试空指针异常
            testNullPointerException();

            // 3. 测试数组越界异常
            testArrayIndexException();

            // 4. 测试自定义异常
            testCustomException();

            // 5. 测试文件操作异常
            testFileOperationException();

        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    private static void testArithmeticException() {
        try {
            int result = 1 / 0;
        } catch (ArithmeticException e) {
            throw new RuntimeException("除零错误", e);
        }
    }

    private static void testNullPointerException() {
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            throw new RuntimeException("空指针错误", e);
        }
    }

    private static void testArrayIndexException() {
        try {
            int[] arr = new int[3];
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("数组越界错误", e);
        }
    }

    private static void testCustomException() {
        try {
            throw new CustomException("自定义异常测试");
        } catch (CustomException e) {
            throw new RuntimeException("自定义异常", e);
        }
    }

    private static void testFileOperationException() {
        try {
            // 尝试读取不存在的文件
            new FileInputStream("nonexistent.txt");
        } catch (IOException e) {
            throw new RuntimeException("文件操作错误", e);
        }
    }
}
