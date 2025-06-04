package Ixiangliang;

import java.util.Scanner;

class Vector3D {
    private int x, y, z;

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(x + other.x, y + other.y, z + other.z);
    }

    public Vector3D sub(Vector3D other) {
        return new Vector3D(x - other.x, y - other.y, z - other.z);
    }

    public Vector3D mul(int scalar) {
        return new Vector3D(x * scalar, y * scalar, z * scalar);
    }

    public double[] div(int scalar) {
        return new double[] {
            (double) x / scalar,
            (double) y / scalar,
            (double) z / scalar
        };
    }

    public double get_length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int z1 = scanner.nextInt();
        
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int z2 = scanner.nextInt();
        
        Vector3D v1 = new Vector3D(x1, y1, z1);
        Vector3D v2 = new Vector3D(x2, y2, z2);
        
        String operation = scanner.next();
        
        switch (operation) {
            case "add":
                System.out.println(v1.add(v2));
                break;
            case "sub":
                System.out.println(v1.sub(v2));
                break;
            case "mul":
                int scalar = scanner.nextInt();
                System.out.println(v1.mul(scalar));
                break;
            case "div":
                int divisor = scanner.nextInt();
                double[] result = v1.div(divisor);
                System.out.printf("%.2f %.2f %.2f\n", result[0], result[1], result[2]);
                break;
            case "get_length":
                System.out.printf("%.2f\n", v1.get_length());
                break;
        }
        
        scanner.close();
    }
}