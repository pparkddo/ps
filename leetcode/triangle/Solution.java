import java.util.Arrays;
import java.util.List;

class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[triangle.get(n-1).size()];

        for (int i = 0; i < triangle.get(n-1).size(); i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        for (int i = n-2; i >= 0; i--) {
            var current = triangle.get(i);
            for (int j = 0; j < current.size(); j++) {
                dp[j] = current.get(j)+ Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }
}