class Solution {

    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        int step = 1;
        for (int number = 1; number <= num; number++) {
            if (step *  2 == number) {
                step *= 2;
            }
            dp[number] = dp[number-step] + 1;
        }
        return dp;
    }
}
