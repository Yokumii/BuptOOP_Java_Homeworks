import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * XCopyNIO程序 - 使用Java NIO.2 API实现
 * 将源目录下的所有子目录及文件复制到目标目录
 * 用法: java XCopyNIO source_dir target_dir
 */
public class XCopyNIO {

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
            System.out.println("用法: java XCopyNIO source_dir target_dir");
            System.out.println("  source_dir: 源目录路径");
            System.out.println("  target_dir: 目标目录路径");
            return;
        }

        String sourceDir = args[0];
        String targetDir = args[1];

        // 创建XCopyNIO实例并执行复制
        XCopyNIO xcopy = new XCopyNIO();
        xcopy.copy(sourceDir, targetDir);
    }

    /**
     * 执行复制操作
     * @param sourcePath 源目录路径
     * @param targetPath 目标目录路径
     */
    public void copy(String sourcePath, String targetPath) {
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);

        // 检查源目录是否存在
        if (!Files.exists(source)) {
            System.err.println("错误: 源目录不存在: " + sourcePath);
            return;
        }

        // 检查源路径是否为目录
        if (!Files.isDirectory(source)) {
            System.err.println("错误: 源路径不是目录: " + sourcePath);
            return;
        }

        // 确保目标目录存在
        if (!Files.exists(target)) {
            if (verbose) {
                System.out.println("创建目标目录: " + targetPath);
            }
            try {
                Files.createDirectories(target);
            } catch (IOException e) {
                System.err.println("错误: 无法创建目标目录: " + targetPath);
                e.printStackTrace();
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
            // 使用文件访问器遍历目录树并复制
            Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                                throws IOException {
                            // 计算相对路径
                            Path relativePath = source.relativize(dir);
                            Path targetDir = target.resolve(relativePath);

                            // 创建目标目录
                            if (!Files.exists(targetDir)) {
                                if (verbose && !dir.equals(source)) { // 不显示源目录本身
                                    System.out.println("创建目录: " + targetDir);
                                }
                                Files.createDirectories(targetDir);
                                totalDirectories++;
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                throws IOException {
                            // 计算相对路径
                            Path relativePath = source.relativize(file);
                            Path targetFile = target.resolve(relativePath);

                            // 复制文件
                            if (verbose) {
                                System.out.println("复制文件: " + file + " -> " + targetFile);
                            }
                            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING,
                                    StandardCopyOption.COPY_ATTRIBUTES);

                            // 更新统计信息
                            totalFiles++;
                            totalBytes += Files.size(file);

                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc)
                                throws IOException {
                            System.err.println("无法访问文件: " + file + " - " + exc.getMessage());
                            return FileVisitResult.CONTINUE;
                        }
                    });

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