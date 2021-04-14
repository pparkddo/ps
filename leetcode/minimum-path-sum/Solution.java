class Solution {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int column = 1; column < n; column++) {
            dp[0][column] = dp[0][column-1] + grid[0][column];
        }

        for (int row = 1; row < m; row++) {
            dp[row][0] = dp[row-1][0] + grid[row][0];
        }

        for (int row = 1; row < m; row++) {
            for (int column = 1; column < n; column++) {
                dp[row][column] = Math.min(dp[row-1][column], dp[row][column-1]) + grid[row][column];
            }
        }

        return dp[m-1][n-1];
    }
}
