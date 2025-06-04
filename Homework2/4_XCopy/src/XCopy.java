import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * XCopy程序
 * 将源目录下的所有子目录及文件复制到目标目录
 * 用法: java XCopy source_dir target_dir
 */
public class XCopy {

    private int totalFiles = 0;       // 总文件数
    private int totalDirectories = 0; // 总目录数
    private long totalBytes = 0;      // 总字节数
    private boolean verbose = true;   // 是否显示详细信息

    /**
     * 主方法，程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 检查参数数量
        if (args.length != 2) {
            System.out.println("用法: java XCopy source_dir target_dir");
            System.out.println("  source_dir: 源目录路径");
            System.out.println("  target_dir: 目标目录路径");
            return;
        }

        String sourceDir = args[0];
        String targetDir = args[1];

        // 创建XCopy实例并执行复制
        XCopy xcopy = new XCopy();
        xcopy.copy(sourceDir, targetDir);
    }

    /**
     * 执行复制操作
     * @param sourcePath 源目录路径
     * @param targetPath 目标目录路径
     */
    public void copy(String sourcePath, String targetPath) {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);

        // 检查源目录是否存在
        if (!sourceFile.exists()) {
            System.err.println("错误: 源目录不存在: " + sourcePath);
            return;
        }

        // 检查源路径是否为目录
        if (!sourceFile.isDirectory()) {
            System.err.println("错误: 源路径不是目录: " + sourcePath);
            return;
        }

        // 确保目标目录存在
        if (!targetFile.exists()) {
            if (verbose) {
                System.out.println("创建目标目录: " + targetPath);
            }
            if (!targetFile.mkdirs()) {
                System.err.println("错误: 无法创建目标目录: " + targetPath);
                return;
            }
        }

        // 重置计数器
        totalFiles = 0;
        totalDirectories = 0;
        totalBytes = 0;

        // 显示操作信息
        System.out.println("正在复制 " + sourcePath + " 到 " + targetPath);

        // 开始时间
        long startTime = System.currentTimeMillis();

        try {
            // 复制目录及文件
            copyDirectory(sourceFile, targetFile);

            // 结束时间
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            // 显示统计信息
            System.out.println("\n复制完成!");
            System.out.println("复制了 " + totalFiles + " 个文件");
            System.out.println("复制了 " + totalDirectories + " 个目录");
            System.out.println("总共 " + formatSize(totalBytes) + " 数据");
            System.out.println("耗时: " + formatTime(elapsedTime));
        } catch (IOException e) {
            System.err.println("复制过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 递归复制目录及其内容
     * @param sourceDir 源目录
     * @param targetDir 目标目录
     * @throws IOException 如果复制过程中发生I/O错误
     */
    private void copyDirectory(File sourceDir, File targetDir) throws IOException {
        // 增加目录计数
        totalDirectories++;

        // 获取源目录中的所有文件和子目录
        File[] files = sourceDir.listFiles();
        if (files == null) {
            return;
        }

        // 遍历所有文件和子目录
        for (File file : files) {
            File targetFile = new File(targetDir, file.getName());

            if (file.isDirectory()) {
                // 如果是目录，创建对应的目标目录并递归复制
                if (verbose) {
                    System.out.println("创建目录: " + targetFile.getPath());
                }
                if (!targetFile.exists() && !targetFile.mkdirs()) {
                    throw new IOException("无法创建目录: " + targetFile.getPath());
                }
                copyDirectory(file, targetFile);
            } else {
                // 如果是文件，直接复制
                copyFile(file, targetFile);
            }
        }
    }

    /**
     * 复制单个文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException 如果复制过程中发生I/O错误
     */
    private void copyFile(File sourceFile, File targetFile) throws IOException {
        if (verbose) {
            System.out.println("复制文件: " + sourceFile.getPath() + " -> " + targetFile.getPath());
        }

        // 使用NIO进行高效复制
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile);
             FileChannel sourceChannel = fis.getChannel();
             FileChannel targetChannel = fos.getChannel()) {

            // 从源通道传输到目标通道
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            // 更新统计信息
            totalFiles++;
            totalBytes += sourceFile.length();
        }

        // 保留文件的最后修改时间
        targetFile.setLastModified(sourceFile.lastModified());
    }

    /**
     * 格式化文件大小显示
     * @param bytes 字节数
     * @return 格式化后的大小字符串
     */
    private String formatSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " 字节";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
        }
    }

    /**
     * 格式化时间显示
     * @param milliseconds 毫秒数
     * @return 格式化后的时间字符串
     */
    private String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        
        if (minutes > 0) {
            return minutes + " 分 " + seconds + " 秒";
        } else {
            return seconds + " 秒";
        }
    }
} 