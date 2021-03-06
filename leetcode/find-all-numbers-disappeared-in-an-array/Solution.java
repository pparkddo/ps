import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = IntStream
                            .rangeClosed(1, nums.length)
                            .boxed()
                            .collect(Collectors.toSet());
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            }
        }
        return set.stream().collect(Collectors.toList());
    }
}