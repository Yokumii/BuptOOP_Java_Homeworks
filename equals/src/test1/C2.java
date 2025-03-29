package test1;

// 子类C2
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
        if (this == obj) return true;
        if (!super.equals(obj)) return false;  // 先判断父类是否相等
        if (!(obj instanceof C2)) return false;
        C2 other = (C2) obj;
        return c == other.c;
    }
}
