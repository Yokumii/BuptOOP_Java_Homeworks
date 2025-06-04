import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 仓库管理器类，管理多个仓库
 * 提供选择最适合存取操作的仓库的方法
 */
public class WarehouseManager {
    private final List<Warehouse> warehouses;
    
    /**
     * 构造一个新的仓库管理器
     * @param warehouseCount 仓库数量
     * @param capacity 每个仓库的容量
     */
    public WarehouseManager(int warehouseCount, int capacity) {
        warehouses = new ArrayList<>();
        for (int i = 0; i < warehouseCount; i++) {
            warehouses.add(new Warehouse(i + 1, capacity));
        }
    }
    
    /**
     * 获取所有仓库
     * @return 仓库列表
     */
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
    
    /**
     * 选择库存最少的仓库用于存储货物
     * 如果有多个仓库库存相同，则选择第一个
     * @param amount 要存储的货物数量
     * @return 选中的仓库，如果没有合适的仓库则返回null
     */
    public synchronized Warehouse selectWarehouseForSave(int amount) {
        // 按库存升序排序
        warehouses.sort(Comparator.comparingInt(Warehouse::getStock));
        
        // 选择第一个可以存储的仓库
        for (Warehouse warehouse : warehouses) {
            if (warehouse.canSave(amount)) {
                return warehouse;
            }
        }
        
        // 如果没有合适的仓库，返回库存最少的仓库
        return warehouses.get(0);
    }
    
    /**
     * 选择库存最多的仓库用于取出货物
     * 如果有多个仓库库存相同，则选择第一个
     * @param amount 要取出的货物数量
     * @return 选中的仓库，如果没有合适的仓库则返回null
     */
    public synchronized Warehouse selectWarehouseForFetch(int amount) {
        // 按库存降序排序
        warehouses.sort((w1, w2) -> Integer.compare(w2.getStock(), w1.getStock()));
        
        // 选择第一个可以取出的仓库
        for (Warehouse warehouse : warehouses) {
            if (warehouse.canFetch(amount)) {
                return warehouse;
            }
        }
        
        // 如果没有合适的仓库，返回库存最多的仓库
        return warehouses.get(0);
    }
    
    /**
     * 打印所有仓库的当前状态
     */
    public void printStatus() {
        for (Warehouse warehouse : warehouses) {
            System.out.print(warehouse + " ");
        }
        System.out.println();
    }
} 