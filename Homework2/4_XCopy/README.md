# XCopy 目录复制程序

## 项目概述

本项目实现了一个名为XCopy的Java程序，用于将一个目录下的所有子目录和文件复制到另一个位置。该程序提供了两种实现方式：一种使用传统的Java IO API，另一种使用更现代的Java NIO.2 API。

## 功能特点

- 递归复制整个目录结构，包括所有子目录和文件
- 保留文件的最后修改时间等属性
- 自动创建目标目录（如果不存在）
- 显示详细的复制过程和统计信息
- 提供两种实现方式，可以根据需要选择

## 项目结构

本项目包含以下几个主要类：

1. **XCopy.java** - 使用传统Java IO API实现的目录复制程序
2. **XCopyNIO.java** - 使用Java NIO.2 API实现的目录复制程序
3. **Main.java** - 主程序入口，允许用户选择使用哪种实现方式

## 使用方法

### 命令行参数

程序可以通过以下命令行参数运行：

```
java Main [-nio] source_dir target_dir
```

其中：
- `-nio`：可选参数，指定使用NIO.2 API实现（通常更高效）
- `source_dir`：源目录路径，要复制的目录
- `target_dir`：目标目录路径，复制的目的地（可以不存在）

### 示例

1. 使用传统IO API复制目录：
   ```
   java Main /path/to/source /path/to/target
   ```

2. 使用NIO.2 API复制目录：
   ```
   java Main -nio /path/to/source /path/to/target
   ```

### 直接使用具体实现类

也可以直接使用具体的实现类：

1. 使用传统IO API：
   ```
   java XCopy /path/to/source /path/to/target
   ```

2. 使用NIO.2 API：
   ```
   java XCopyNIO /path/to/source /path/to/target
   ```

## 实现细节

### XCopy（传统IO实现）

XCopy类使用Java传统的File API实现目录复制功能：

1. 使用递归方法遍历源目录结构
2. 对于目录，创建对应的目标目录
3. 对于文件，使用FileChannel进行高效复制
4. 保留文件的最后修改时间

### XCopyNIO（NIO.2实现）

XCopyNIO类使用Java 7引入的NIO.2 API实现目录复制功能：

1. 使用Files.walkFileTree方法遍历源目录结构
2. 实现SimpleFileVisitor接口处理不同类型的文件系统对象
3. 使用Files.copy方法复制文件，自动保留文件属性
4. 使用相对路径计算确保目录结构的正确复制

## 性能比较

一般情况下，NIO.2 API实现的版本在处理大量文件时会有更好的性能，原因包括：

1. 使用walkFileTree方法进行高效的目录遍历
2. Files.copy方法针对不同操作系统进行了优化
3. 更好的异常处理和错误恢复机制

## 注意事项

- 程序不会复制符号链接本身，而是复制链接指向的文件或目录
- 如果目标位置已存在同名文件，将会被覆盖
- 复制大文件或大量文件时，可能需要较长时间
- 确保有足够的磁盘空间用于复制
- 确保对源目录有读取权限，对目标位置有写入权限 