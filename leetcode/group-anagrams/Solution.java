import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> words = new HashMap<>();
        for (int index = 0; index < strs.length; index++) {
            String each = strs[index];
            char[] array = each.toCharArray();
            Arrays.sort(array);
            String sorted = String.valueOf(array);
            List<String> ws = words.getOrDefault(sorted, new ArrayList<>());
            ws.add(each);
            words.put(sorted, ws);
        }
        List<List<String>> answer = new ArrayList<>();
        for (String key : words.keySet()) {
            answer.add(words.get(key));
        }
        return answer;
    }
}
