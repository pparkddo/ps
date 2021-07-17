import java.util.Arrays;

class Solution {

    int MOD = 20170805;
    private static final int NO_PASS = 1;
    private static final int NO_TURN = 2;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    private static final int NOT_VISITED = -1;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], NOT_VISITED);
            }
        }
        return (getWayCount(m-1, n-2, HORIZONTAL, cityMap, dp) + getWayCount(m-2, n-1, VERTICAL, cityMap, dp)) % MOD;
    }

    private int getWayCount(int row, int column, int direction, int[][] cityMap, int[][][] dp) {
        if (!isValid(row, column, cityMap)) {
            return 0;
        }
        if (dp[row][column][direction] != NOT_VISITED) {
            return dp[row][column][direction];
        }
        if (row == 0 && column == 0) {
            return 1;
        }
        if (cityMap[row][column] == NO_PASS) {
            return 0;
        }
        if (cityMap[row][column] == NO_TURN) {
            if (direction == HORIZONTAL) {
                if (!isValid(row, column-1, cityMap)) {
                    return 0;
                }
                return dp[row][column-1][direction] = getWayCount(row, column-1, direction, cityMap, dp) % MOD;
            }
            if (direction == VERTICAL) {
                if (!isValid(row-1, column, cityMap)) {
                    return 0;
                }
                return dp[row-1][column][direction] = getWayCount(row-1, column, direction, cityMap, dp) % MOD;
            }
        }
        int horizontalCount = 0;
        if (isValid(row, column-1, cityMap)) {
            horizontalCount = dp[row][column-1][HORIZONTAL] = getWayCount(row, column-1, HORIZONTAL, cityMap, dp);
        }
        int verticalCount = 0;
        if (isValid(row-1, column, cityMap)) {
            verticalCount = dp[row-1][column][VERTICAL] = getWayCount(row-1, column, VERTICAL, cityMap, dp);
        }
        return (horizontalCount + verticalCount) % MOD;
    }

    private boolean isValid(int row, int column, int[][] cityMap) {
        return row >= 0 && row < cityMap.length && column >= 0 && column < cityMap[0].length;
    }
}
