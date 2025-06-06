package test2;

public class C2 extends P {
    private int c;

    public C2(int p, int c) {
        super(p);
        this.c = c;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof P)) return false;
        
        // 如果obj是P的任何子类，检查c是否相等
        if (obj instanceof C1 || obj instanceof C2 /* ... 或其他子类 */) {
            // 获取子类的c值
            int otherC;
            if (obj instanceof C1) {
                otherC = ((C1) obj).getC();
            } else if (obj instanceof C2) {
                otherC = ((C2) obj).getC();
            } /* ... 其他子类 */ else {
                return false; // 未知子类
            }
            return this.getC() == otherC;
        }
        
        return false; // obj是P但不是任何已知子类
    }
}
