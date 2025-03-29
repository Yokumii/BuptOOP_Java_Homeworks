public class CustomExceptionTest {
    public static void main(String[] args) {
        try {
            // 测试场景1：创建账户
            System.out.println("=== 测试创建账户 ===");
            BankAccount account1 = new BankAccount("123456", 1000);
            System.out.println("账户创建成功：" + account1.getAccountNumber());

            // 测试场景2：创建无效账户
            System.out.println("\n=== 测试创建无效账户 ===");
            try {
                BankAccount account2 = new BankAccount("12345", 500);  // 号码过短
            } catch (InvalidAccountException e) {
                System.out.println("创建账户失败：" + e.getMessage());
                System.out.println("无效账号：" + e.getAccountNumber());
            }

            // 测试场景3：正常取款
            System.out.println("\n=== 测试正常取款 ===");
            account1.withdraw(500);
            System.out.println("取款成功，当前余额：" + account1.getBalance());

            // 测试场景4：余额不足
            System.out.println("\n=== 测试余额不足 ===");
            try {
                account1.withdraw(1000);
            } catch (InsufficientBalanceException e) {
                System.out.println("取款失败：" + e.getMessage());
                System.out.println(e.getDetails());
            }

            // 测试场景5：非法取款金额
            System.out.println("\n=== 测试非法取款金额 ===");
            try {
                account1.withdraw(-100);
            } catch (IllegalArgumentException e) {
                System.out.println("取款失败：" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("发生未预期的错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
