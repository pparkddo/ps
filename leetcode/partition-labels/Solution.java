import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> positions = new HashMap<>();
        for (int index = 0; index < S.length(); index++) {
            char c = S.charAt(index);
            positions.put(c, index);
        }
        
        List<Integer> answer = new ArrayList<>();
        int anchor = 0;
        int partitionIndex = 0;
        for (int index = 0; index < S.length(); index++) {
            char c = S.charAt(index);
            partitionIndex = Math.max(partitionIndex, positions.get(c));
            if (index == partitionIndex) {
                answer.add(index - anchor + 1);
                anchor = index + 1;
            }
        }
        return answer;
    }
}
