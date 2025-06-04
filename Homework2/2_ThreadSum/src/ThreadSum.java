/**
 * 多线程求和程序
 * 创建10个线程，每个线程计算特定范围内的数字之和
 * 第一个线程对sum从1加到10，第二个线程对sum从11加到20...第十个线程对sum从91加到100
 */
public class ThreadSum {
    // 共享的求和变量，使用volatile确保多线程环境下的可见性
    private static volatile int sum = 0;
    
    public static void main(String[] args) {
        System.out.println("开始多线程求和计算...");
        
        // 创建10个线程
        Thread[] threads = new Thread[10];
        
        // 初始化并启动每个线程
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            final int start = threadNum * 10 + 1;
            final int end = start + 9;
            
            threads[i] = new Thread(() -> {
                int localSum = 0;
                
                // 计算当前线程负责范围内的数字之和
                for (int j = start; j <= end; j++) {
                    localSum += j;
                }
                
                // 将局部和加到全局和上
                synchronized (ThreadSum.class) {
                    sum += localSum;
                    System.out.println("线程 " + threadNum + " 计算完成：从 " + start + " 加到 " + end + 
                                      "，局部和为 " + localSum + "，当前总和为 " + sum);
                }
            });
            
            threads[i].start();
        }
        
        // 等待所有线程完成
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 输出最终结果
        System.out.println("\n所有线程计算完成！");
        System.out.println("从1加到100的和为：" + sum);
        System.out.println("理论结果为：" + (100 * 101 / 2)); // 高斯求和公式：n*(n+1)/2
    }
} 