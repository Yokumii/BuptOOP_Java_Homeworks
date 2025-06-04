package Fhousuobianli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int[] postorder = new int[input.length];
        
        for (int i = 0; i < input.length; i++) {
            postorder[i] = Integer.parseInt(input[i]);
        }
        
        System.out.println(verifyPostorder(postorder));
        scanner.close();
    }
    
    private static boolean verifyPostorder(int[] postorder) {
        return check(postorder, 0, postorder.length - 1);
    }
    
    private static boolean check(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        int root = postorder[end];
        int i = start;
        
        while (i < end && postorder[i] < root) {
            i++;
        }
        
        int mid = i;
        while (i < end) {
            if (postorder[i] < root) {
                return false;
            }
            i++;
        }
        
        return check(postorder, start, mid - 1) && check(postorder, mid, end - 1);
    }
}