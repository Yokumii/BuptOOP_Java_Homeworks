public class AdvancedDisplay implements Displayable {
    private String content;
    private int priority;

    public AdvancedDisplay(String content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    // 接口方法
    @Override
    public void display() {
        System.out.println("显示内容：" + content);
    }

    // 类自己的方法（非接口方法）
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void showDetails() {
        System.out.println("详细信息 - 内容: " + content + ", 优先级: " + priority);
    }
}