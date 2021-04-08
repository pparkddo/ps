import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int answer = Integer.MAX_VALUE;

    private static boolean isCorrectlyAdjusted(int n, int h, int[][] ladders) {
        for (int column = 1; column <= n; column++) {
            int current = column;
            for (int row = 1; row <= h; row++) {
                current += ladders[row][current];
            }
            if (current != column) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int row, int count, int n, int h, int[][] ladders) {
        if (count > 3 || answer < count) {
            return;
        }
        if (isCorrectlyAdjusted(n, h, ladders)) {
            answer = Math.min(answer, count);
            return;
        }
        for (int r = row; r <= h; r++) {
            for (int c = 1; c < n; c++) {
                if (ladders[r][c] != 0 || ladders[r][c+1] != 0) {
                    continue;
                }
                ladders[r][c] = 1;
                ladders[r][c+1] = -1;
                dfs(r, count+1, n, h, ladders);
                ladders[r][c] = ladders[r][c+1] = 0;
            }
        }
    }

    private static int solution(int n, int m, int h, int[][] ladders) {
        dfs(1, 0, n, h, ladders);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmh = in.readLine().split(" ");
        int n = Integer.parseInt(nmh[0]);
        int m = Integer.parseInt(nmh[1]);
        int h = Integer.parseInt(nmh[2]);
        int[][] ladders = new int[h+1][n+1];
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int row = Integer.parseInt(each[0]);
            int column = Integer.parseInt(each[1]);
            ladders[row][column] = 1;
            ladders[row][column+1] = -1;
        }
        in.close();

        System.out.println(solution(n, m, h, ladders));
    }
}
