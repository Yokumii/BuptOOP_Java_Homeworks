package Hwenjiancaozuo;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String content = scanner.nextLine();        // 第1行
            String mode = scanner.nextLine();           // 第2行
            String appendContent = scanner.nextLine();  // 第3行

            String fileName = "test.txt";
            File file = new File(fileName);

            // 第一步：覆盖写入 content
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();

            switch (mode) {
                case "r":
                    break;
                case "r+":
                    RandomAccessFile raf1 = new RandomAccessFile(file, "rw");
                    raf1.seek(0);
                    raf1.write(appendContent.getBytes());
                    raf1.close();
                    break;
                case "a":
                case "a+":
                    FileOutputStream fa = new FileOutputStream(file, true);
                    fa.write(appendContent.getBytes());
                    fa.close();
                    break;
                case "w":
                case "w+":
                    FileOutputStream fw = new FileOutputStream(file);
                    fw.write(appendContent.getBytes());
                    fw.close();
                    break;
                case "x":
                case "x+":
                    if (file.exists()) {
                        break;
                    } else {
                        FileOutputStream fx = new FileOutputStream(file);
                        fx.write(appendContent.getBytes());
                        fx.close();
                    }
                    break;
            }

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();

            System.out.print(new String(buffer));

            scanner.close();
        } catch (IOException e) {
            System.out.println();
        }
    }
}