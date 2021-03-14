import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> positions = new HashMap<>();
        int answer = 0;
        int start = 0;
        for (int index = 0; index < s.length(); index++) {
            char each = s.charAt(index);
            if (positions.containsKey(each)) {
                start = Math.max(start, positions.get(each)+1);
            }
            positions.put(each, index);
            int length = index - start + 1;
            answer = Math.max(answer, length);
        }
        return answer;
    }
}
