import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 交易记录类
class Transaction {
    private LocalDateTime time;
    private String type;  // 存款、取款、转账
    private double amount;
    private double balance;  // 交易后余额
    private String description;

    public Transaction(String type, double amount, double balance, String description) {
        this.time = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | 余额: %.2f | %s",
                time, type, amount, balance, description);
    }
}

// 银行账户类
class BankAccount {
    private String accountNumber;  // 账号
    private String name;          // 储户姓名
    private LocalDateTime openTime;  // 开户时间
    private String idNumber;      // 身份证号码
    private double balance;       // 存款余额
    private double transferLimit; // 转账额度
    private boolean isActive;     // 账户状态
    private List<Transaction> transactions;  // 交易记录

    // 构造函数（开户）
    public BankAccount(String accountNumber, String name, String idNumber,
                       double initialDeposit, double transferLimit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("初始存款不能为负数");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.idNumber = idNumber;
        this.balance = initialDeposit;
        this.transferLimit = transferLimit;
        this.openTime = LocalDateTime.now();
        this.isActive = true;
        this.transactions = new ArrayList<>();

        // 记录开户交易
        addTransaction("开户", initialDeposit, "开户初始存款");
    }

    // 存款
    public void deposit(double amount) {
        checkAccountActive();
        if (amount <= 0) {
            throw new IllegalArgumentException("存款金额必须大于0");
        }

        balance += amount;
        addTransaction("存款", amount, "现金存款");
    }

    // 取款
    public void withdraw(double amount) {
        checkAccountActive();
        if (amount <= 0) {
            throw new IllegalArgumentException("取款金额必须大于0");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("余额不足");
        }

        balance -= amount;
        addTransaction("取款", -amount, "现金取款");
    }

    // 转账
    public void transfer(BankAccount target, double amount) {
        checkAccountActive();
        if (!target.isActive) {
            throw new IllegalStateException("目标账户已关闭");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("转账金额必须大于0");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("余额不足");
        }
        if (amount > transferLimit) {
            throw new IllegalArgumentException("超出转账限额");
        }

        balance -= amount;
        target.balance += amount;

        // 记录转出交易
        addTransaction("转出", -amount, "转账至" + target.accountNumber);
        // 记录转入交易
        target.addTransaction("转入", amount, "来自" + this.accountNumber);
    }

    // 查询余额
    public double getBalance() {
        checkAccountActive();
        return balance;
    }

    // 查询交易明细
    public void printTransactions() {
        checkAccountActive();
        System.out.println("账户: " + accountNumber + " 的交易明细：");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    // 销户
    public void closeAccount() {
        checkAccountActive();
        if (balance > 0) {
            throw new IllegalStateException("账户还有余额，无法销户");
        }
        isActive = false;
        addTransaction("销户", 0, "账户已关闭");
    }

    // 添加交易记录
    private void addTransaction(String type, double amount, String description) {
        transactions.add(new Transaction(type, amount, balance, description));
    }

    // 检查账户是否有效
    private void checkAccountActive() {
        if (!isActive) {
            throw new IllegalStateException("账户已关闭");
        }
    }

    // 获取账户信息
    public String getAccountInfo() {
        return String.format("账号: %s\n姓名: %s\n开户时间: %s\n身份证号: %s\n" +
                        "余额: %.2f\n转账限额: %.2f\n账户状态: %s",
                accountNumber, name, openTime, idNumber, balance, transferLimit,
                isActive ? "正常" : "已关闭");
    }
}

// 测试类
public class BankAccountTest {
    public static void main(String[] args) {
        try {
            // 1. 测试开户
            System.out.println("=== 测试开户 ===");
            BankAccount account1 = new BankAccount("1001", "张三", "110101199001011234",
                    1000, 5000);
            BankAccount account2 = new BankAccount("1002", "李四", "110101199001011235",
                    2000, 5000);
            System.out.println(account1.getAccountInfo());

            // 2. 测试存款
            System.out.println("\n=== 测试存款 ===");
            account1.deposit(500);
            System.out.println("存款后余额: " + account1.getBalance());

            // 3. 测试取款
            System.out.println("\n=== 测试取款 ===");
            account1.withdraw(200);
            System.out.println("取款后余额: " + account1.getBalance());

            // 4. 测试转账
            System.out.println("\n=== 测试转账 ===");
            account1.transfer(account2, 6000);
            System.out.println("账户1余额: " + account1.getBalance());
            System.out.println("账户2余额: " + account2.getBalance());

            // 5. 测试查询交易明细
            System.out.println("\n=== 测试查询交易明细 ===");
            account1.printTransactions();

            // 6. 测试异常情况
            System.out.println("\n=== 测试异常情况 ===");
            try {
                account1.withdraw(10000);  // 余额不足
            } catch (IllegalArgumentException e) {
                System.out.println("预期异常: " + e.getMessage());
            }

            try {
                account1.transfer(account2, 6000);  // 超出转账限额
            } catch (IllegalArgumentException e) {
                System.out.println("预期异常: " + e.getMessage());
            }

            // 7. 测试销户
            System.out.println("\n=== 测试销户 ===");
            try {
                account1.closeAccount();  // 还有余额，无法销户
            } catch (IllegalStateException e) {
                System.out.println("预期异常: " + e.getMessage());
            }

            // 取出所有余额
            account1.withdraw(account1.getBalance());
            account1.closeAccount();

            // 尝试对已关闭账户操作
            try {
                account1.deposit(100);
            } catch (IllegalStateException e) {
                System.out.println("预期异常: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("发生异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}