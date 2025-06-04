/**
 * 存货线程类，向仓库中存入货物
 */
public class SaveThread extends Thread {
    private final int id;                    // 线程ID
    private final WarehouseManager manager;  // 仓库管理器
    private int amount;                      // 当前要存入的货物数量
    private boolean hasNewOperation = false; // 是否有新的操作
    
    /**
     * 构造一个新的存货线程
     * @param id 线程ID
     * @param manager 仓库管理器
     */
    public SaveThread(int id, WarehouseManager manager) {
        super("Save-" + id);
        this.id = id;
        this.manager = manager;
    }
    
    /**
     * 设置要存入的货物数量
     * @param amount 货物数量
     */
    public synchronized void setAmount(int amount) {
        this.amount = amount;
        this.hasNewOperation = true;
        notify(); // 唤醒线程执行操作
    }
    
    /**
     * 等待新的操作
     * @throws InterruptedException 如果线程被中断
     */
    private synchronized void waitForOperation() throws InterruptedException {
        while (!hasNewOperation) {
            wait();
        }
        hasNewOperation = false;
    }
    
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                // 等待新的操作
                waitForOperation();
                
                // 选择合适的仓库
                Warehouse warehouse = manager.selectWarehouseForSave(amount);
                
                try {
                    // 尝试存入货物
                    int saved = warehouse.save(amount);
                    
                    // 打印操作结果
                    System.out.println("Save线程" + id + " 向仓库" + warehouse.getId() + 
                                      " 存入了 " + saved + " 个货物");
                    
                    // 打印所有仓库状态
                    manager.printStatus();
                } catch (InterruptedException e) {
                    System.out.println("Save线程" + id + " 被中断");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Save线程" + id + " 被中断");
        }
    }
} 