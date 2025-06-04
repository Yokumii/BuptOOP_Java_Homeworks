/**
 * 多线程实现的看门狗定时器程序
 * 线程A：执行主要工作，并每3秒将watchdog变量修改为1
 * 线程B：每1秒监测watchdog变量，如果值为1则修改为0，如果连续10次读到0则判断A线程出错
 */
public class WatchdogTimerDemo {
    // 共享的看门狗变量
    private static volatile int watchdog = 0;
    // 记录连续读到0的次数
    private static int zeroCount = 0;
    // 控制线程A是否应该继续运行
    private static volatile boolean threadARunning = true;
    // 线程A的引用，用于重启
    private static Thread threadA;

    public static void main(String[] args) {
        System.out.println("启动看门狗定时器程序...");

        // 创建并启动线程A
        startThreadA();

        // 创建并启动线程B（监控线程）
        Thread threadB = new Thread(() -> {
            System.out.println("线程B（监控线程）已启动");
            while (true) {
                // 每1秒检查一次watchdog变量
                try {
                    Thread.sleep(1000);
                    
                    if (watchdog == 1) {
                        System.out.println("线程B: 检测到watchdog=1，重置为0");
                        watchdog = 0;
                        zeroCount = 0; // 重置连续读到0的计数
                    } else {
                        zeroCount++;
                        System.out.println("线程B: watchdog=0，连续读到0的次数: " + zeroCount);
                        
                        if (zeroCount >= 10) {
                            System.out.println("警告: 线程A可能出错！连续10次读到watchdog=0");
                            
                            // 选做部分：重启线程A
                            threadARunning = false; // 停止当前的线程A
                            System.out.println("正在尝试重启线程A...");
                            
                            // 等待线程A完全停止
                            try {
                                threadA.join(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            
                            // 重启线程A
                            startThreadA();
                            
                            // 重置计数
                            zeroCount = 0;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        threadB.setDaemon(true); // 设置为守护线程，主线程结束时B也结束
        threadB.start();

        // 让主线程等待一段时间后退出
        try {
            Thread.sleep(60000); // 运行60秒后退出
            System.out.println("程序运行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动线程A的方法
     */
    private static void startThreadA() {
        threadARunning = true;
        threadA = new Thread(() -> {
            System.out.println("线程A（工作线程）已启动");
            int counter = 0;
            
            while (threadARunning) {
                try {
                    // 模拟线程A的主要工作
                    System.out.println("线程A: 正在执行工作... 计数: " + counter);
                    counter++;
                    
                    // 每3秒将watchdog设置为1
                    Thread.sleep(3000);
                    watchdog = 1;
                    System.out.println("线程A: 已将watchdog设置为1");
                    
                    // 模拟线程A可能出错的情况（运行30秒后停止更新watchdog）
                    if (counter > 10) {
                        System.out.println("线程A: 模拟出错情况，停止更新watchdog");
                        // 不再更新watchdog，等待被线程B检测到并重启
                        Thread.sleep(20000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("线程A被中断");
                    break;
                }
            }
            System.out.println("线程A已停止");
        });
        threadA.start();
    }
} 