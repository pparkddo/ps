import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dp;

    private static boolean isAvailable(int row, int column) {
        return (
            row >= 0
            && row < map.length
            && column >= 0
            && column < map[0].length
        );
    }

    private static int getCount(int row, int column) {
        if (row == map.length-1 && column == map[0].length-1) {
            return 1;
        }
        if (dp[row][column] != -1) {
            return dp[row][column];
        }
        // 이미 왔던 길을 돌아가는 경우는 없으므로 visited 배열 불필요함
        // 왜냐하면 map 의 값이 감소하는 부분으로만 이동하기 때문
        int sum = 0;
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(nextRow, nextColumn)) {
                continue;
            }
            if (map[row][column] <= map[nextRow][nextColumn]) {
                continue;
            }
            sum += getCount(nextRow, nextColumn);
        }
        return dp[row][column] = sum;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm =in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        map = new int[n][m];
        dp = new int[n][m];
        for (int[] each : dp) {
            Arrays.fill(each, -1);
        }
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        System.out.println(getCount(0, 0));
    }
}
