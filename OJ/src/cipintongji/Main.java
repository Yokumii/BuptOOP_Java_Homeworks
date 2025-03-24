package cipintongji;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        int[] wordInArticles = new int[m + 1];
        int[] wordTotalCount = new int[m + 1];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            int length = Integer.parseInt(input[0]);

            HashSet<Integer> uniqueWords = new HashSet<>();

            for (int j = 1; j <= length; j++) {
                int wordId = Integer.parseInt(input[j]);
                wordTotalCount[wordId]++;
                uniqueWords.add(wordId);
            }

            for (int wordId : uniqueWords) {
                wordInArticles[wordId]++;
            }
        }

        scanner.close();

        for (int i = 1; i <= m; i++) {
            System.out.println(wordInArticles[i] + " " + wordTotalCount[i]);
        }
    }
}