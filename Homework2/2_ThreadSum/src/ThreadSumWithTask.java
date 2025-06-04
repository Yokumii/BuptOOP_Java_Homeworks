/**
 * 多线程求和程序（使用任务类实现）
 * 创建10个线程，每个线程计算特定范围内的数字之和
 * 第一个线程对sum从1加到10，第二个线程对sum从11加到20...第十个线程对sum从91加到100
 */
public class ThreadSumWithTask {
    // 共享的求和变量
    private static int sum = 0;
    
    // 用于同步的对象锁
    private static final Object lock = new Object();
    
    public static void main(String[] args) {
        System.out.println("开始多线程求和计算（任务类实现）...");
        
        // 创建10个线程和对应的任务
        Thread[] threads = new Thread[10];
        
        // 初始化并启动每个线程
        for (int i = 0; i < 10; i++) {
            int start = i * 10 + 1;
            int end = start + 9;
            
            ThreadSumTask task = new ThreadSumTask(i, start, end);
            threads[i] = new Thread(task);
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
    
    /**
     * 求和任务类，实现Runnable接口
     */
    static class ThreadSumTask implements Runnable {
        private final int threadNum;  // 线程编号
        private final int start;      // 起始数字
        private final int end;        // 结束数字
        
        public ThreadSumTask(int threadNum, int start, int end) {
            this.threadNum = threadNum;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public void run() {
            int localSum = 0;
            
            // 计算当前线程负责范围内的数字之和
            for (int i = start; i <= end; i++) {
                localSum += i;
            }
            
            // 将局部和加到全局和上
            synchronized (lock) {
                sum += localSum;
                System.out.println("线程 " + threadNum + " 计算完成：从 " + start + " 加到 " + end + 
                                  "，局部和为 " + localSum + "，当前总和为 " + sum);
            }
        }
    }
} 