package solution1;

class Solution {

    private int[] piles;
    private int[] suffixSum;
    private int[][] dp;

    public int stoneGameII(int[] piles) {
        this.piles = piles;

        int n = piles.length;

        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        dp = new int[n][n];
        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (i == piles.length) {
            return 0;
        }
        if (2 * m >= piles.length - i) {
            return suffixSum[i];
        }
        if (dp[i][m] != 0) {
            return dp[i][m];
        }

        int min = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * m; x++) {
            min = Math.min(min, dfs(i + x, Math.max(m, x)));
        }
        return dp[i][m] = suffixSum[i] - min;
    }

    public static void main(String[] args) {
        new Solution().stoneGameII(new int[]{2, 7, 9, 4, 4});
    }
}
