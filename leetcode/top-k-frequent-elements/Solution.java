import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0)+1);
        }
        return counts.entrySet()
                     .stream()
                     .sorted((a, b) -> -a.getValue().compareTo(b.getValue()))
                     .limit(k)
                     .mapToInt(each -> each.getKey())
                     .toArray();
    }
}