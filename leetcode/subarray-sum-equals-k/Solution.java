import java.util.HashMap;
import java.util.Map;

class Solution {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);

        int answer = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSum.containsKey(sum-k)) {
                answer += prefixSum.get(sum-k);
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0)+1);
        }
        return answer;
    }
}