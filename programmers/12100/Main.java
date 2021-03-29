import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int answer = 0;
    static int[] directions = {0, 1, 2, 3};  // UP, DOWN, RIGHT, LEFT

    private static int getMax(int[][] board) {
        int max = 0;
        for (int[] row : board) {
            for (int value : row) {
                max = Math.max(max, value);
            }
        }
        return max;
    }

    private static int[][] cloneBoard(int[][] board) {
        int[][] clone = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            clone[i] = board[i].clone();
        }
        return clone;
    }

    private static int getNextIndex(int[][] board, int currentIndex, int fixedAxis, int direction) {
        switch (direction) {
            case 0:
                for (int index = currentIndex+1; index < board.length; index++) {
                    if (board[index][fixedAxis] != 0) {
                        return index;
                    }
                }
                return -1;
            case 1:
                for (int index = currentIndex-1; index >= 0; index--) {
                    if (board[index][fixedAxis] != 0) {
                        return index;
                    }
                }
                return -1;
            case 2:
                for (int index = currentIndex-1; index >= 0; index--) {
                    if (board[fixedAxis][index] != 0) {
                        return index;
                    }
                }
                return -1;
            case 3:
                for (int index = currentIndex+1; index < board[0].length; index++) {
                    if (board[fixedAxis][index] != 0) {
                        return index;
                    }
                }
                return -1;
        }
        return -1;
    }

    private static void arrange(int[][] board, int fixedAxis, int direction) {
        List<Integer> values = new ArrayList<>();

        switch (direction) {
            case 0:
                for (int index = 0; index < board.length; index++) {
                    if (board[index][fixedAxis] != 0) {
                        values.add(board[index][fixedAxis]);
                    }
                    board[index][fixedAxis] = 0;
                }
                int upIndex = 0;
                for (Integer value : values) {
                    board[upIndex++][fixedAxis] = value;
                }
                break;
            case 1:
                for (int index = board.length-1; index >= 0; index--) {
                    if (board[index][fixedAxis] != 0) {
                        values.add(board[index][fixedAxis]);
                    }
                    board[index][fixedAxis] = 0;
                }
                int downIndex = board.length-1;
                for (Integer value : values) {
                    board[downIndex--][fixedAxis] = value;
                }
                break;
            case 2:
                for (int index = board[0].length-1; index >= 0; index--) {
                    if (board[fixedAxis][index] != 0) {
                        values.add(board[fixedAxis][index]);
                    }
                    board[fixedAxis][index] = 0;
                }
                int leftIndex = board[0].length-1;
                for (Integer value : values) {
                    board[fixedAxis][leftIndex--] = value;
                }
                break;
            case 3:
                for (int index = 0; index < board[0].length; index++) {
                    if (board[fixedAxis][index] != 0) {
                        values.add(board[fixedAxis][index]);
                    }
                    board[fixedAxis][index] = 0;
                }
                int rightIndex = 0;
                for (Integer value : values) {
                    board[fixedAxis][rightIndex++] = value;
                }
                break;
        }
    }

    private static int[][] getMovedBoard(int[][] board, int direction) {
        int[][] moved = cloneBoard(board);

        switch (direction) {
            case 0:
                for (int column = 0; column < moved[0].length; column++) {
                    for (int row = 0; row < moved.length; row++) {
                        int nextIndex = getNextIndex(moved, row, column, direction);
                        if (nextIndex == -1) {
                            continue;
                        }
                        if (moved[row][column] == moved[nextIndex][column]) {
                            moved[row][column] += moved[nextIndex][column];
                            moved[nextIndex][column] = 0;
                        }
                    }
                    arrange(moved, column, direction);
                }
                break;
            case 1:
                for (int column = 0; column < moved[0].length; column++) {
                    for (int row = moved.length-1; row >= 0; row--) {
                        int nextIndex = getNextIndex(moved, row, column, direction);
                        if (nextIndex == -1) {
                            continue;
                        }
                        if (moved[row][column] == moved[nextIndex][column]) {
                            moved[row][column] += moved[nextIndex][column];
                            moved[nextIndex][column] = 0;
                        }
                    }
                    arrange(moved, column, direction);
                }
                break;
            case 2:
                for (int row = 0; row < moved.length; row++) {
                    for (int column = moved[0].length-1; column >= 0; column--) {
                        int nextIndex = getNextIndex(moved, column, row, direction);
                        if (nextIndex == -1) {
                            continue;
                        }
                        if (moved[row][column] == moved[row][nextIndex]) {
                            moved[row][column] += moved[row][nextIndex];
                            moved[row][nextIndex] = 0;
                        }
                    }
                    arrange(moved, row, direction);
                }
                break;
            case 3:
                for (int row = 0; row < moved.length; row++) {
                    for (int column = 0; column < moved[0].length; column++) {
                        int nextIndex = getNextIndex(moved, column, row, direction);
                        if (nextIndex == -1) {
                            continue;
                        }
                        if (moved[row][column] == moved[row][nextIndex]) {
                            moved[row][column] += moved[row][nextIndex];
                            moved[row][nextIndex] = 0;
                        }
                    }
                    arrange(moved, row, direction);
                }
                break;
        }

        return moved;
    }

    private static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            answer = Math.max(answer, getMax(board));
            return;
        }
        for (int direction : directions) {
            int[][] moved = getMovedBoard(board, direction);
            dfs(moved, depth+1);
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < board.length; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        dfs(board, 0);

        System.out.println(answer);
    }
}
