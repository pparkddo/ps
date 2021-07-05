import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int WALL = 1;

    private static int solution(int n, int[][] house) {
        return getCaseCount(n, house);
    }

    private static int getCaseCount(int n, int[][] house) {
        int[][][] dp = new int[n][n][DIRECTION.values().length];

        int h = DIRECTION.HORIZON.value();
        int v = DIRECTION.VERTICAL.value();
        int d = DIRECTION.DIAGONAL.value();
        dp[0][1][h] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (house[i][j] == WALL) {
                    continue;
                }
                dp[i][j][h] = dp[i][j-1][h] + dp[i][j-1][d];
                if (i == 0) {
                    continue;
                }
                dp[i][j][v] = dp[i-1][j][v] + dp[i-1][j][d];
                if (house[i-1][j] == WALL || house[i][j-1] == WALL) {
                    continue;
                }
                dp[i][j][d] = dp[i-1][j-1][h] + dp[i-1][j-1][v] + dp[i-1][j-1][d];
            }
        }

        int lastCell = n - 1;
        return dp[lastCell][lastCell][h] + dp[lastCell][lastCell][v] + dp[lastCell][lastCell][d];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] house = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, house));
    }
}

enum DIRECTION {

    HORIZON(0),
    VERTICAL(1),
    DIAGONAL(2);

    final private int value;

    DIRECTION(int value) {
        this.value = value;
    }

    int value() {
        return value;
    }
}
