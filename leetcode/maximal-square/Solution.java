import java.util.Arrays;

class Solution {

    public int maximalSquare(char[][] matrix) {
        int[][] dp = getDpTable(matrix);
        int maximumLength = getMaximumLength(dp);
        return maximumLength * maximumLength;
    }

    private int getMaximumLength(int[][] dp) {
        return Arrays.stream(dp).flatMapToInt(each -> Arrays.stream(each)).max().getAsInt();
    }

    private int[][] getDpTable(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[i][j] = convertToInt(matrix[i][j]);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            }
        }
        return dp;
    }

    private int convertToInt(char value) {
        return value - '0';
    }
}