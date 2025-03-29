# 如何创建自定义异常

1. **创建自定义异常类**
```java
class InsufficientBalanceException extends Exception {
    // 添加特定于该异常的属性
    private double balance;
    private double amount;
    
    // 构造函数
    public InsufficientBalanceException(String message, double balance, double amount) {
        super(message);
        this.balance = balance;
        this.amount = amount;
    }
}
```

2. **抛出异常**
```java
if (balance < amount) {
    throw new InsufficientBalanceException("余额不足", balance, amount);
}
```

3. **处理异常**
```java
try {
    account.withdraw(1000);
} catch (InsufficientBalanceException e) {
    System.out.println("取款失败：" + e.getMessage());
    System.out.println(e.getDetails());
}
```