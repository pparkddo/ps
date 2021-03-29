class Solution {
    
    int[] dp;
    
    private int getCount(int n) {
        if (dp[n] != 0) {
            return dp[n];
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return dp[n] = (getCount(n-1) + getCount(n-2)) % 1_000_000_007;
    }
    
    public int solution(int n) {
        dp = new int[n+1];
        return getCount(n);
    }
}
