import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<Integer>> indexesBySortedWord = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            var chars = strs[i].toCharArray();
            Arrays.sort(chars);
            var sortedWord = Arrays.toString(chars);
            List<Integer> indexes = indexesBySortedWord.getOrDefault(sortedWord, new ArrayList<>());
            indexes.add(i);
            indexesBySortedWord.put(sortedWord, indexes);
        }

        List<List<String>> answer = new ArrayList<>();
        for (var key : indexesBySortedWord.keySet()) {
            answer.add(indexesBySortedWord.get(key).stream().map(index -> strs[index]).toList());
        }
        return answer;
    }
}
