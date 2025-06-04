package Jsaolei;

import java.util.*;

public class Main {
    private static final int MAXSIZE = 35;
    private static int[][] board;
    private static int[][] boardStatus;
    private static int[][] thunderNumber;
    private static int rows, cols, thunder;
    private static int thunderLeft;
    private static int operations = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int level = scanner.nextInt();
        switch (level) {
            case 1: rows = 9; cols = 9; thunder = 10; break;
            case 2: rows = 16; cols = 16; thunder = 40; break;
            case 3: rows = 16; cols = 30; thunder = 99; break;
            case 4:
                rows = scanner.nextInt();
                cols = scanner.nextInt();
                thunder = scanner.nextInt();
                break;
        }
        
        board = new int[MAXSIZE][MAXSIZE];
        boardStatus = new int[MAXSIZE][MAXSIZE];
        thunderNumber = new int[MAXSIZE][MAXSIZE];
        thunderLeft = thunder;
        
        scanner.nextLine();
        for (int i = 1; i <= rows; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < cols; j++) {
                board[i][j + 1] = line.charAt(j) == '*' ? 1 : 0;
            }
        }
        
        createThunderNumber();
        
        while (scanner.hasNextInt()) {
            int op = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            
            operations++;
            if (x < 1 || x > rows || y < 1 || y > cols || 
                (op != 1 && op != 2 && op != 3 && op != 4 && op != 9)) {
                printStatus(false);
                continue;
            }
            
            switch (op) {
                case 1: leftClick(x, y); break;
                case 2: rightClick1(x, y); break;
                case 3: rightClick2(x, y); break;
                case 4: rightClick3(x, y); break;
                case 9: doubleClick(x, y); break;
            }
            
            thunderLeft = countThunderLeft();
            int result = judge();
            printStatus(result != 1);
            
            if (result != 1) break;
        }
        
        scanner.close();
    }
    
    private static void createThunderNumber() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int sum = 0;
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = i + di;
                        int nj = j + dj;
                        if (ni >= 1 && ni <= rows && nj >= 1 && nj <= cols) {
                            sum += board[ni][nj];
                        }
                    }
                }
                thunderNumber[i][j] = sum - board[i][j];
            }
        }
    }
    
    private static void leftClick(int x, int y) {
        if (x < 1 || x > rows || y < 1 || y > cols || 
            boardStatus[x][y] == 4 || boardStatus[x][y] == 3) {
            return;
        }
        
        boardStatus[x][y] = 4;
        if (thunderNumber[x][y] == 0 && board[x][y] != 1) {
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    int ni = x + di;
                    int nj = y + dj;
                    if (ni >= 1 && ni <= rows && nj >= 1 && nj <= cols && 
                        (ni != x || nj != y)) {
                        leftClick(ni, nj);
                    }
                }
            }
        }
    }
    
    private static void rightClick1(int x, int y) {
        if (boardStatus[x][y] == 0 || boardStatus[x][y] == 2) {
            boardStatus[x][y] = 3;
        }
    }

    private static void rightClick2(int x, int y) {
        if (boardStatus[x][y] == 0 || boardStatus[x][y] == 3) {
            boardStatus[x][y] = 2;
        }
    }

    private static void rightClick3(int x, int y) {
        if (boardStatus[x][y] == 2 || boardStatus[x][y] == 3) {
            boardStatus[x][y] = 0;
        }
    }

    private static void doubleClick(int x, int y) {
        if (boardStatus[x][y] != 4) return;
        int sum = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ni = x + di;
                int nj = y + dj;
                if (ni >= 1 && ni <= rows && nj >= 1 && nj <= cols) {
                    if (boardStatus[ni][nj] == 3) sum++;
                }
            }
        }
        if (sum == thunderNumber[x][y]) {
            boolean hitMine = false;
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    int ni = x + di;
                    int nj = y + dj;
                    if (ni >= 1 && ni <= rows && nj >= 1 && nj <= cols) {
                        if (boardStatus[ni][nj] != 3 && board[ni][nj] == 1) {
                            boardStatus[ni][nj] = 4;
                            hitMine = true;
                        }
                    }
                }
            }
            if (!hitMine) {
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = x + di;
                        int nj = y + dj;
                        if (ni >= 1 && ni <= rows && nj >= 1 && nj <= cols) {
                            if (boardStatus[ni][nj] != 3) {
                                if (thunderNumber[ni][nj] == 0) {
                                    leftClick(ni, nj);
                                }
                                boardStatus[ni][nj] = 4;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int countThunderLeft() {
        int count = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (boardStatus[i][j] == 3) count++;
            }
        }
        return thunder - count;
    }

    private static int judge() {
        int is = 1, win = 1;
        for (int i = 1; i <= rows && is == 1; i++) {
            for (int j = 1; j <= cols && is == 1; j++) {
                if (board[i][j] == 1 && boardStatus[i][j] == 4) {
                    is = 0;
                    break;
                }
                if (board[i][j] == 0 && boardStatus[i][j] != 4) {
                    win = 0;
                }
            }
        }
        if (is == 1 && win == 1) return 2;
        else return is;
    }

    private static void printStatus(boolean end) {
        int result = judge();
        if (result == 1) {
            System.out.println("Game in progress");
            print1();
        } else if (result == 0) {
            System.out.println("Hit mine, you lose");
            print2();
        } else if (result == 2) {
            System.out.println("Game over, you win");
            print2();
        }
    }

    private static void print1() {
        System.out.println(operations + " " + thunderLeft);
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (boardStatus[i][j] == 0) System.out.print(".");
                else if (boardStatus[i][j] == 2) System.out.print("?");
                else if (boardStatus[i][j] == 3) System.out.print("!");
                else if (boardStatus[i][j] == 4) System.out.print(thunderNumber[i][j]);
            }
            System.out.println();
        }
    }

    private static void print2() {
        System.out.println(operations + " " + thunderLeft);
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (board[i][j] == 1) System.out.print("*");
                else if (boardStatus[i][j] == 0) System.out.print(".");
                else if (boardStatus[i][j] == 2) System.out.print("?");
                else if (boardStatus[i][j] == 3) System.out.print("!");
                else if (boardStatus[i][j] == 4) System.out.print(thunderNumber[i][j]);
            }
            System.out.println();
        }
    }
} 