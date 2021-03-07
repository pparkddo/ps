import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String[][] board;
    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int answer = Integer.MIN_VALUE;
    private static boolean[] visited = new boolean[26];

    private static boolean isPossible(int row, int column) {
        return (
            row >= 0
            && row < board.length
            && column >= 0
            && column < board[0].length
        );
    }

    private static int getAlphabetOrder(String alphabet) {
        return alphabet.charAt(0) - 'A';
    }

    private static void dfs(int row, int column, int depth) {
        String currentAlphabet = board[row][column];
        int alphabetOrder = getAlphabetOrder(currentAlphabet);
        if (visited[alphabetOrder]) {
            return;
        }
        answer = Math.max(answer, depth);
        visited[alphabetOrder] = true;
        for (int i = 0; i < dr.length; i++) {
            int newRow = row + dr[i];
            int newColumn = column + dc[i];
            if (!isPossible(newRow, newColumn)) {
                continue;
            }
            dfs(newRow, newColumn, depth+1);
        }
        visited[alphabetOrder] = false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);

        board = new String[r][c];
        for (int i = 0; i < r; i++) {
            String[] each = in.readLine().split("");
            for (int j = 0; j < c; j++) {
                board[i][j] = each[j];
            }
        }

        in.close();

        dfs(0, 0, 1);
        System.out.println(answer);
    }
}
