import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int BLANK = 0;
    private static final int CHEESE = 1;
    private static final int TO_BE_MELTED = 2;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int clear(int[][] board, boolean[][] visited) {
        int clearCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                visited[i][j] = false;
                if (board[i][j] == TO_BE_MELTED) {
                    board[i][j] = BLANK;
                    clearCount++;
                }
            }
        }
        return clearCount;
    }

    private static int getCheeseCount(int[][] board) {
        int count = 0;
        for (int[] row : board) {
            for (int each : row) {
                if (each == CHEESE) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid(int row, int column, int[][] board) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length;
    }

    private static void dfs(int row, int column, int[][] board, boolean[][] visited) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        if (board[row][column] == CHEESE) {
            board[row][column] = TO_BE_MELTED;
            return;
        }
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isValid(nextRow, nextColumn, board)) {
                continue;
            }
            dfs(nextRow, nextColumn, board, visited);
        }
    }

    private static String solution(int h, int w, int[][] board) {
        StringBuilder answer = new StringBuilder();

        int cheeseCount = getCheeseCount(board);
        if (cheeseCount == 0) {
            return answer.append(0).append("\n").append(0).toString().trim();
        }

        int timeSpend = 0;
        int lastCheeseCount = cheeseCount;
        boolean[][] visited = new boolean[h][w];
        while (true) {
            dfs(0, 0, board, visited);
            int clearCount = clear(board, visited);
            if (clearCount == 0) {
                break;
            }
            timeSpend++;
            lastCheeseCount = clearCount;
        }

        return answer.append(timeSpend).append("\n").append(lastCheeseCount).toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] hw = in.readLine().split(" ");
        int h = Integer.parseInt(hw[0]);
        int w = Integer.parseInt(hw[1]);
        int[][] board = new int[h][w];
        for (int i = 0; i < h; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(h, w, board));
    }
}
