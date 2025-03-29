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
        if (this == obj) return true;
        if (obj == null) return false;
        // 检查是否是P的子类
        if (!(obj instanceof P)) return false;
        // 检查父类属性
        if (((P)obj).p != this.p) return false;
        // 如果对象是P的子类，检查c值
        if (obj instanceof C1) {
            return this.c == ((C1)obj).getC();
        } else if (obj instanceof C2) {
            return this.c == ((C2)obj).c;
        }
        return false;
    }
}
