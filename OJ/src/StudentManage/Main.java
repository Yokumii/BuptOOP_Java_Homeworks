package StudentManage;

import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private int mathScore;
    private int englishScore;
    private int javaScore;

    public Student(String id, String name, int mathScore, int englishScore, int javaScore) {
        this.id = id;
        this.name = name;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.javaScore = javaScore;
    }

    public String getId() {
        return id;
    }

    public double getAverageScore() {
        return (mathScore + englishScore + javaScore) / 3.0;
    }

    public void updateScores(int mathScore, int englishScore, int javaScore) {
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.javaScore = javaScore;
    }

    @Override
    public String toString() {
        return "Student ID:" + id + "\n" +
                "Name:" + name + "\n" +
                "Average Score:" + String.format("%.1f", getAverageScore());
    }
}

class StudentManager {
    private static HashMap<String, Student> studentMap = new HashMap<>();

    public static String addStudent(String id, String name, int mathScore, int englishScore, int javaScore) {
        if (studentMap.containsKey(id)) {
            return "Students already exist";
        }
        studentMap.put(id, new Student(id, name, mathScore, englishScore, javaScore));
        return "Add success";
    }

    public static String deleteStudent(String id) {
        if (!studentMap.containsKey(id)) {
            return "Students do not exist";
        }
        studentMap.remove(id);
        return "Delete success";
    }

    public static String updateStudent(String id, int mathScore, int englishScore, int javaScore) {
        if (!studentMap.containsKey(id)) {
            return "Students do not exist";
        }
        studentMap.get(id).updateScores(mathScore, englishScore, javaScore);
        return "Update success";
    }

    public static String getStudentInfo(String id) {
        if (!studentMap.containsKey(id)) {
            return "Students do not exist";
        }
        return studentMap.get(id).toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            int operation = Integer.parseInt(input[0]);
            switch (operation) {
                case 1:
                    System.out.println(StudentManager.addStudent(input[1], input[2], Integer.parseInt(input[3]), Integer.parseInt(input[4]), Integer.parseInt(input[5])));
                    break;
                case 2:
                    System.out.println(StudentManager.deleteStudent(input[1]));
                    break;
                case 3:
                    System.out.println(StudentManager.updateStudent(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4])));
                    break;
                case 4:
                    System.out.println(StudentManager.getStudentInfo(input[1]));
                    break;
            }
        }
        scanner.close();
    }
}
