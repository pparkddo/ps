import java.util.HashMap;
import java.util.Map;

class Solution {
    
    private int dfs(int[] nums, int target, int sum, int depth, Map<String, Integer> dp) {
        String s = depth + "_" + sum;
        if (dp.containsKey(s)) {
            return dp.get(s);
        }
        if (depth == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        dp.put(s, dfs(nums, target, sum+nums[depth], depth+1, dp) + dfs(nums, target, sum-nums[depth], depth+1, dp));
        return dp.get(s);
    }
    
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0, new HashMap<>());
    }
}