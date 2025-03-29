public class BankAccount {
    private String accountNumber;
    private double balance;
    private boolean isActive;

    public BankAccount(String accountNumber, double initialBalance)
            throws InvalidAccountException {
        // 验证账号格式
        if (!isValidAccountNumber(accountNumber)) {
            throw new InvalidAccountException("无效的账号格式", accountNumber);
        }

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.isActive = true;
    }

    // 取款方法
    public void withdraw(double amount)
            throws InsufficientBalanceException, InvalidAccountException {
        // 检查账户状态
        if (!isActive) {
            throw new InvalidAccountException("账户已关闭", accountNumber);
        }

        // 检查取款金额
        if (amount <= 0) {
            throw new IllegalArgumentException("取款金额必须大于0");
        }

        // 检查余额
        if (balance < amount) {
            throw new InsufficientBalanceException(
                    "余额不足", balance, amount);
        }

        // 执行取款
        balance -= amount;
    }

    // 验证账号格式的辅助方法
    private boolean isValidAccountNumber(String accountNumber) {
        // 这里简化处理，实际应该有更复杂的验证逻辑
        return accountNumber != null && accountNumber.matches("\\d{6}");
    }

    // getter方法
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}