// 通知类
public class Notice implements Displayable {
    private String content;

    public Notice(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        System.out.println("通知内容：" + content);
    }
}