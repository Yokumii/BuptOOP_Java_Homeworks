import java.util.Scanner;

/**
 * 仓库系统主程序类
 * 创建三个仓库，两个fetch线程和一个save线程
 * 处理用户输入并执行相应的操作
 */
public class WarehouseSystem {
    private static final int WAREHOUSE_COUNT = 3;  // 仓库数量
    private static final int WAREHOUSE_CAPACITY = 20; // 仓库容量
    
    public static void main(String[] args) {
        System.out.println("仓库系统启动...");
        System.out.println("创建 " + WAREHOUSE_COUNT + " 个仓库，每个仓库容量为 " + WAREHOUSE_CAPACITY);
        
        // 创建仓库管理器
        WarehouseManager manager = new WarehouseManager(WAREHOUSE_COUNT, WAREHOUSE_CAPACITY);
        
        // 创建并启动fetch线程
        FetchThread fetch1 = new FetchThread(1, manager);
        FetchThread fetch2 = new FetchThread(2, manager);
        fetch1.start();
        fetch2.start();
        
        // 创建并启动save线程
        SaveThread save = new SaveThread(1, manager);
        save.start();
        
        // 打印初始状态
        System.out.println("初始仓库状态:");
        manager.printStatus();
        
        // 处理用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n请输入操作（格式：线程ID 数量）：");
        System.out.println("1 表示fetch1线程，2 表示fetch2线程，3 表示save线程");
        System.out.println("例如：1 3 表示fetch1线程取3个单位货物");
        System.out.println("输入 exit 退出程序");
        
        while (true) {
            String input = scanner.nextLine().trim();
            
            // 检查是否退出
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            
            try {
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("输入格式错误，请重新输入");
                    continue;
                }
                
                int threadId = Integer.parseInt(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                
                // 验证数量范围
                if (amount < 1 || amount > 5) {
                    System.out.println("货物数量必须在1-5之间，请重新输入");
                    continue;
                }
                
                // 根据线程ID执行相应操作
                switch (threadId) {
                    case 1:
                        fetch1.setAmount(amount);
                        break;
                    case 2:
                        fetch2.setAmount(amount);
                        break;
                    case 3:
                        save.setAmount(amount);
                        break;
                    default:
                        System.out.println("无效的线程ID，请重新输入");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入格式错误，请重新输入");
            }
        }
        
        // 关闭资源
        scanner.close();
        System.out.println("正在关闭线程...");
        fetch1.interrupt();
        fetch2.interrupt();
        save.interrupt();
        
        try {
            fetch1.join();
            fetch2.join();
            save.join();
        } catch (InterruptedException e) {
            System.out.println("主线程被中断");
        }
        
        System.out.println("仓库系统已关闭");
    }
} 