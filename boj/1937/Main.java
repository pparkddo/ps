import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int n, int[][] forest) {
        int[][] dp = new int[n][n];
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                max = Math.max(max, dfs(row, column, forest, dp));
            }
        }
        return max;
    }

    private static int dfs(int row, int column, int[][] forest, int[][] dp) {
        if (dp[row][column] != 0) {
            return dp[row][column];
        }
        int day = 1;
        int value = forest[row][column];
        forest[row][column] = 0;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn, forest)) {
                continue;
            }
            if (forest[nextRow][nextColumn] <= value) {
                continue;
            }
            day = Math.max(day, dfs(nextRow, nextColumn, forest, dp)+1);
        }
        forest[row][column] = value;
        return dp[row][column] = day;
    }

    private static boolean isValid(int row, int column, int[][] forest) {
        return row >= 0 && row < forest.length && column >= 0 && column < forest[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] forest=  new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                forest[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, forest));
    }
}
