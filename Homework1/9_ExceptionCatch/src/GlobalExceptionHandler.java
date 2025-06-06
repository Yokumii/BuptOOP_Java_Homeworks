import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GlobalExceptionHandler {
    private static final Logger logger;

    // 静态初始化日志器
    static {
        try {
            // 创建日志文件
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            // 如果日志初始化失败，使用控制台输出
            System.err.println("日志初始化失败: " + e.getMessage());
            throw new RuntimeException("日志系统初始化失败");
        }
    }

    // 处理所有类型的异常
    public static void handleException(Throwable e) {
        try {
            // 1. 记录异常信息
            String errorMessage = String.format("异常发生时间: %s\n异常类型: %s\n异常信息: %s\n堆栈跟踪:\n",
                    new Date(), e.getClass().getName(), e.getMessage());

            // 2. 记录堆栈跟踪
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errorMessage += sw.toString();

            // 3. 写入日志
            logger.severe(errorMessage);

            // 4. 控制台输出
            System.err.println("发生异常，详细信息已记录到日志文件");
            System.err.println("异常摘要: " + e.getMessage());

        } catch (Exception loggingError) {
            System.err.println("异常处理过程中出错: " + loggingError.getMessage());
            System.err.println("原始异常: " + e.getMessage());
        }
    }
}
