package Afenshu;

import java.util.Scanner;

class Rational {
    private int top;
    private int bottom;
    
    public Rational(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        reduce();
    }
    
    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private void reduce() {
        int divisor = findGCD(Math.abs(top), Math.abs(bottom));
        top /= divisor;
        bottom /= divisor;
        if (bottom < 0) {
            top = -top;
            bottom = -bottom;
        }
    }
    
    public Rational plus(Rational other) {
        int newTop = top * other.bottom + other.top * bottom;
        int newBottom = bottom * other.bottom;
        return new Rational(newTop, newBottom);
    }
    
    public Rational minus(Rational other) {
        int newTop = top * other.bottom - other.top * bottom;
        int newBottom = bottom * other.bottom;
        return new Rational(newTop, newBottom);
    }
    
    public Rational times(Rational other) {
        return new Rational(top * other.top, bottom * other.bottom);
    }
    
    public Rational over(Rational other) {
        return new Rational(top * other.bottom, bottom * other.top);
    }
    
    public Rational inverse() {
        return new Rational(bottom, top);
    }
    
    public double asDecimal() {
        return (double) top / bottom;
    }
    
    @Override
    public String toString() {
        return top + "/" + bottom;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = scanner.nextInt();
        }
        
        Rational a = new Rational(nums[0], nums[1]);
        Rational b = new Rational(nums[2], nums[3]);
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.plus(b));
        System.out.println(a.minus(b));
        System.out.println(a.times(b));
        System.out.println(a.over(b));
        System.out.println(a.inverse());
        System.out.printf("%.1f\n", a.asDecimal());
        
        scanner.close();
    }
}