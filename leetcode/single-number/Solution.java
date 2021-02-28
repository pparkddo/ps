import java.util.Set;
import java.util.HashSet;

class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                continue;
            }
            set.add(num);
        }
        return set.stream().findFirst().get();
    }
}
