/**
 * 可重启版看门狗定时器程序
 * 实现选做部分：当B发现A出错时重新启动A线程
 */
public class RestartableWatchdogTimer {
    // 共享的看门狗变量
    private static volatile int watchdog = 0;
    // 记录连续读到0的次数
    private static int zeroCount = 0;

    public static void main(String[] args) {
        System.out.println("启动可重启版看门狗定时器程序...");
        
        // 创建工作线程工厂
        WorkerThreadFactory factory = new WorkerThreadFactory();
        
        // 使用数组来存储线程引用，使其可以在lambda表达式中修改
        final Thread[] workerThreadHolder = new Thread[1];
        
        // 创建并启动初始工作线程
        workerThreadHolder[0] = factory.createWorkerThread();
        workerThreadHolder[0].start();
        
        // 创建并启动监控线程
        Thread monitorThread = new Thread(() -> {
            System.out.println("监控线程已启动");
            
            while (true) {
                try {
                    // 每1秒检查一次watchdog变量
                    Thread.sleep(1000);
                    
                    if (watchdog == 1) {
                        System.out.println("监控线程: 检测到watchdog=1，重置为0");
                        watchdog = 0;
                        zeroCount = 0; // 重置连续读到0的计数
                    } else {
                        zeroCount++;
                        System.out.println("监控线程: watchdog=0，连续读到0的次数: " + zeroCount);
                        
                        if (zeroCount >= 10) {
                            System.out.println("警告: 工作线程可能出错！连续10次读到watchdog=0");
                            
                            // 选做部分：重启工作线程
                            System.out.println("正在尝试重启工作线程...");
                            
                            // 尝试中断当前的工作线程
                            workerThreadHolder[0].interrupt();
                            
                            // 等待工作线程完全停止
                            try {
                                workerThreadHolder[0].join(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            
                            // 创建并启动新的工作线程
                            workerThreadHolder[0] = factory.createWorkerThread();
                            workerThreadHolder[0].start();
                            
                            // 重置计数
                            zeroCount = 0;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("监控线程被中断");
                    break;
                }
            }
        });
        monitorThread.setDaemon(true); // 设置为守护线程
        monitorThread.start();
        
        // 让主线程等待一段时间后退出
        try {
            Thread.sleep(120000); // 运行120秒后退出
            System.out.println("程序运行结束");
            workerThreadHolder[0].interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 工作线程工厂类，用于创建工作线程
     */
    static class WorkerThreadFactory {
        private int threadCounter = 0;
        
        /**
         * 创建一个新的工作线程
         */
        public Thread createWorkerThread() {
            threadCounter++;
            final int threadId = threadCounter;
            
            return new Thread(() -> {
                System.out.println("工作线程-" + threadId + " 已启动");
                int counter = 0;
                
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        // 模拟线程的主要工作
                        System.out.println("工作线程-" + threadId + ": 正在执行工作... 计数: " + counter);
                        counter++;
                        
                        // 每3秒将watchdog设置为1
                        Thread.sleep(3000);
                        watchdog = 1;
                        System.out.println("工作线程-" + threadId + ": 已将watchdog设置为1");
                        
                        // 模拟线程可能出错的情况（运行30秒后停止更新watchdog）
                        if (counter > 10) {
                            System.out.println("工作线程-" + threadId + ": 模拟出错情况，停止更新watchdog");
                            Thread.sleep(30000); // 模拟长时间阻塞
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("工作线程-" + threadId + " 被中断");
                }
                
                System.out.println("工作线程-" + threadId + " 已停止");
            });
        }
    }
} 