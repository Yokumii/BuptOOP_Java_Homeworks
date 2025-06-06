package test1;

// 父类P
public class P {
    protected int p;

    public P(int p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        P other = (P) obj;
        return p == other.p;
    }
}
