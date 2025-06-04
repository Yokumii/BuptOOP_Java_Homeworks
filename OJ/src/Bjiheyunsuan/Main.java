package Bjiheyunsuan;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        List<Integer> listA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listA.add(scanner.nextInt());
        }
        Collections.sort(listA);
        
        int m = scanner.nextInt();
        List<Integer> listB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            listB.add(scanner.nextInt());
        }
        Collections.sort(listB);
        
        List<Integer> common = new ArrayList<>();
        List<Integer> all = new ArrayList<>();
        List<Integer> diff = new ArrayList<>();
        
        int i = 0, j = 0;
        while (i < listA.size() && j < listB.size()) {
            if (listA.get(i).equals(listB.get(j))) {
                common.add(listA.get(i));
                all.add(listA.get(i));
                i++;
                j++;
            } else if (listA.get(i) < listB.get(j)) {
                all.add(listA.get(i));
                diff.add(listA.get(i));
                i++;
            } else {
                all.add(listB.get(j));
                j++;
            }
        }
        
        while (i < listA.size()) {
            all.add(listA.get(i));
            diff.add(listA.get(i));
            i++;
        }
        
        while (j < listB.size()) {
            all.add(listB.get(j));
            j++;
        }
        
        output(common);
        output(all);
        output(diff);
        
        scanner.close();
    }
    
    private static void output(List<Integer> list) {
        if (list.isEmpty()) {
            System.out.println();
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
