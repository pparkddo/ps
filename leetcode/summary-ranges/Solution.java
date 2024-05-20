import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        List<String> answer = new ArrayList<>();
        int start = nums[0];
        int end = nums[0];
        for (int num : nums) {
            if (end + 1 == num || end == num) {
                end = num;
            } else {
                if (start == end) {
                    answer.add(String.valueOf(start));
                } else {
                    answer.add(start + "->" + end);
                }
                start = num;
                end = num;
            }
        }

        if (start == end) {
            answer.add(String.valueOf(start));
        } else {
            answer.add(start + "->" + end);
        }

        return answer;
    }
}
