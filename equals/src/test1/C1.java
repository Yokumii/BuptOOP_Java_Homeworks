package test1;

// 子类C1
public class C1 extends P {
    private int c;

    public C1(int p, int c) {
        super(p);
        this.c = c;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;  // 先判断父类是否相等
        if (!(obj instanceof C1)) return false;
        C1 other = (C1) obj;
        return c == other.c;
    }
}
