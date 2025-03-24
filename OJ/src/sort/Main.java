package sort;

import java.util.*;
import java.io.*;

class Student {
    String name;
    int year, month, day;

    public Student(String name, int year, int month, int day) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return name + " " + year + " " + month + " " + day;
    }

    public static final Comparator<Student> NAME_COMPARATOR = Comparator.comparing(s -> s.name);
    public static final Comparator<Student> BIRTHDAY_COMPARATOR = Comparator
            .comparingInt((Student s) -> s.year)
            .thenComparingInt(s -> s.month)
            .thenComparingInt(s -> s.day);
}

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8")); // 确保输出 UTF-8
        Scanner scanner = new Scanner(System.in, "UTF-8"); // 读取时使用 UTF-8

        int T = scanner.nextInt();
        String type = scanner.next();

        if (type.equals("Integer")) {
            Integer[] numbers = new Integer[T];
            for (int i = 0; i < T; i++) {
                numbers[i] = scanner.nextInt();
            }
            Arrays.sort(numbers);
            System.out.println(String.join(" ", Arrays.stream(numbers).map(String::valueOf).toArray(String[]::new)));

        } else if (type.equals("Student")) {
            String sortMethod = scanner.next();
            Student[] students = new Student[T];

            for (int i = 0; i < T; i++) {
                String name = scanner.next();
                int year = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();
                students[i] = new Student(name, year, month, day);
            }

            if (sortMethod.equals("name")) {
                Arrays.sort(students, Student.NAME_COMPARATOR);
            } else if (sortMethod.equals("birthday")) {
                Arrays.sort(students, Student.BIRTHDAY_COMPARATOR);
            }

            // 单行输出所有学生
            System.out.println(String.join(" ", Arrays.stream(students).map(Student::toString).toArray(String[]::new)));
        }
        scanner.close();
    }
}