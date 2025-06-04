/**
 * XCopy主程序入口
 * 提供两种复制目录的实现方式
 * 用法: java Main [-nio] dir1 dir2
 */
public class Main {
    
    /**
     * 主方法，程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }
        
        boolean useNio = false;
        String sourceDir;
        String targetDir;
        
        // 解析参数
        if (args[0].equals("-nio")) {
            useNio = true;
            if (args.length < 3) {
                printUsage();
                return;
            }
            sourceDir = args[1];
            targetDir = args[2];
        } else {
            sourceDir = args[0];
            targetDir = args[1];
        }
        
        // 根据选择使用不同的实现
        if (useNio) {
            System.out.println("使用NIO.2 API实现的XCopy");
            XCopyNIO xcopy = new XCopyNIO();
            xcopy.copy(sourceDir, targetDir);
        } else {
            System.out.println("使用传统IO API实现的XCopy");
            XCopy xcopy = new XCopy();
            xcopy.copy(sourceDir, targetDir);
        }
    }
    
    /**
     * 打印使用说明
     */
    private static void printUsage() {
        System.out.println("用法: java Main [-nio] source_dir target_dir");
        System.out.println("  -nio: 可选参数，使用NIO.2 API实现（更高效）");
        System.out.println("  source_dir: 源目录路径");
        System.out.println("  target_dir: 目标目录路径");
        System.out.println("例如:");
        System.out.println("  java Main dir1 dir2");
        System.out.println("  java Main -nio dir1 dir2");
    }
} 