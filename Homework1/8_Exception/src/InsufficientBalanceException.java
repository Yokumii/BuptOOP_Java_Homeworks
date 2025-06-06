class InsufficientBalanceException extends Exception {
    private double balance;    // 当前余额
    private double amount;     // 请求金额

    // 构造函数
    public InsufficientBalanceException(String message, double balance, double amount) {
        super(message);
        this.balance = balance;
        this.amount = amount;
    }

    // 获取详细信息
    public String getDetails() {
        return String.format("当前余额: %.2f, 请求金额: %.2f, 差额: %.2f",
                balance, amount, amount - balance);
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }
}