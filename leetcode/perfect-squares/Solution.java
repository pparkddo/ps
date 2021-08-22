class Solution {

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int number = 1; number <= n; number++) {
            int min = number;
            for (int each = 1; each*each <= number; each++) {
                int square = each * each;
                int rest = number - square;
                min = Math.min(min, dp[rest]+1);
            }
            dp[number] = min;
        }
        return dp[n];
    }
}