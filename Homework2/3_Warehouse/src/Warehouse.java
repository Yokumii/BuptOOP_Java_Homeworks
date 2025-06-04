/**
 * 仓库类，表示一个具有特定容量的仓库
 * 支持存取货物操作，并提供同步机制
 */
public class Warehouse {
    private final int id;            // 仓库编号
    private int stock;               // 当前库存
    private final int capacity;      // 仓库容量
    
    /**
     * 构造一个新的仓库
     * @param id 仓库编号
     * @param capacity 仓库容量
     */
    public Warehouse(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.stock = 0;
    }
    
    /**
     * 获取仓库编号
     * @return 仓库编号
     */
    public int getId() {
        return id;
    }
    
    /**
     * 获取当前库存
     * @return 当前库存
     */
    public synchronized int getStock() {
        return stock;
    }
    
    /**
     * 获取仓库容量
     * @return 仓库容量
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * 检查是否可以存入指定数量的货物
     * @param amount 要存入的货物数量
     * @return 如果可以存入返回true，否则返回false
     */
    public synchronized boolean canSave(int amount) {
        return stock + amount <= capacity;
    }
    
    /**
     * 检查是否可以取出指定数量的货物
     * @param amount 要取出的货物数量
     * @return 如果可以取出返回true，否则返回false
     */
    public synchronized boolean canFetch(int amount) {
        return stock >= amount;
    }
    
    /**
     * 存入货物
     * @param amount 要存入的货物数量
     * @return 实际存入的货物数量
     * @throws InterruptedException 如果线程被中断
     */
    public synchronized int save(int amount) throws InterruptedException {
        // 检查是否可以存入
        while (!canSave(amount)) {
            wait(); // 等待直到有足够的空间
        }
        
        // 存入货物
        stock += amount;
        
        // 通知所有等待的线程
        notifyAll();
        
        return amount;
    }
    
    /**
     * 取出货物
     * @param amount 要取出的货物数量
     * @return 实际取出的货物数量
     * @throws InterruptedException 如果线程被中断
     */
    public synchronized int fetch(int amount) throws InterruptedException {
        // 检查是否可以取出
        while (!canFetch(amount)) {
            wait(); // 等待直到有足够的货物
        }
        
        // 取出货物
        stock -= amount;
        
        // 通知所有等待的线程
        notifyAll();
        
        return amount;
    }
    
    @Override
    public String toString() {
        return "仓库" + id + ": " + stock + "/" + capacity;
    }
} 