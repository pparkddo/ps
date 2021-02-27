import java.util.Arrays;

class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
                continue;
            }
            dp[i] = dp[i-1] + nums[i];
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
