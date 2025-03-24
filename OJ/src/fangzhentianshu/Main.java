package fangzhentianshu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        try {
            n = scanner.nextInt();

            if (n < 1 || n > 50) {
                System.out.println("Input data error");
                return;
            }

            int [][] matrix = new int[n][n];
            int num = 1;
            int startRow = 0, endRow = n - 1;
            int startCol = n - 1, endCol = 0;

            while (num <= n * n) {
                // Top to bottom
                for (int i = startRow; i <= endRow && num <= n*n; i++) {
                    matrix[i][startCol] = num++;
                }
                startCol--;

                // Right to left
                for (int j = startCol; j >= endCol && num <= n*n; j--) {
                    matrix[endRow][j] = num++;
                }
                endRow--;

                // Bottom to top
                for (int i = endRow; i >= startRow && num <= n*n; i--) {
                    matrix[i][endCol] = num++;
                }
                endCol++;

                // Left to right
                for (int j = endCol; j <= startCol && num <= n*n; j++) {
                    matrix[startRow][j] = num++;
                }startRow++;


            }

            // Print the matrix
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j > 0) System.out.print(" ");
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Input data error");
        } finally {
            scanner.close();
        }
    }
}
