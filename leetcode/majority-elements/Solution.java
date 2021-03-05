import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            int count = counts.getOrDefault(num, 0) + 1;
            counts.put(num, count);
        }
        return counts
                .entrySet()
                .stream()
                .filter(each -> each.getValue() > nums.length/2)
                .findFirst()
                .get()
                .getKey();
    }
}
