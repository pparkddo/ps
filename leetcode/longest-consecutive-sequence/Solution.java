import java.util.HashMap;
import java.util.Map;

class Solution {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, 0);
        }

        for (int num : nums) {
            int consecutiveNum = num - 1;
            if (count.containsKey(consecutiveNum)) {
                count.put(num, 1);
            }
        }

        int answer = 0;
        for (int num : count.keySet()) {
            count.put(num, traverse(count, num));
            answer = Math.max(answer, count.get(num));
        }
        return answer + 1;
    }

    private int traverse(Map<Integer, Integer> count, int start) {
        int result = 0;
        int num = start;
        while (count.get(num) != 0) {
            result += count.get(num);
            count.put(num, 0);
            num--;
        }
        return result;
    }
}