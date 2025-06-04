package Gduilie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int initialLength = scanner.nextInt();
        scanner.nextLine();

        String[] initialElements = scanner.nextLine().split(" ");
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> outElements = new ArrayList<>();

        for (int i = 0; i < initialLength; i++) {
            queue.offer(Integer.parseInt(initialElements[i]));
        }

        for (int i = 0; i < 2; i++) {
            String[] operation = scanner.nextLine().split(" ");

            if (operation[0].equals("out")) {
                int count = Integer.parseInt(operation[1]);
                int actualCount = Math.min(count, queue.size());

                for (int j = 0; j < actualCount; j++) {
                    outElements.add(queue.poll());
                }
            } else {
                for (int j = 1; j < operation.length; j++) {
                    queue.offer(Integer.parseInt(operation[j]));
                }
            }
        }

        System.out.print("len = " + queue.size());
        if (!queue.isEmpty()) {
            System.out.print(", data = ");
            List<Integer> queueList = new ArrayList<>(queue);
            for (int i = 0; i < queueList.size(); i++) {
                System.out.print(queueList.get(i));
                if (i < queueList.size() - 1) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();

        System.out.print("len = " + outElements.size());
        if (!outElements.isEmpty()) {
            System.out.print(", data = ");
            for (int i = 0; i < outElements.size(); i++) {
                System.out.print(outElements.get(i));
                if (i < outElements.size() - 1) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();

        scanner.close();
    }
}
