package Efanyizifuchuan;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(decode(input));
        scanner.close();
    }
    
    private static String decode(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                
                i++;
                
                int start = i;
                int count = 1;
                while (count > 0) {
                    if (s.charAt(i) == '[') count++;
                    if (s.charAt(i) == ']') count--;
                    i++;
                }
                
                String subStr = decode(s.substring(start, i - 1));
                
                for (int j = 0; j < num; j++) {
                    result.append(subStr);
                }
            } else {
                result.append(c);
                i++;
            }
        }
        
        return result.toString();
    }
}