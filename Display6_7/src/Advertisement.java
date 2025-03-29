// 广告类
public class Advertisement implements Displayable {
    private String message;

    public Advertisement(String message) {
        this.message = message;
    }

    @Override
    public void display() {
        System.out.println("广告消息：" + message);
    }
}
