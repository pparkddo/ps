// https://programmers.co.kr/learn/courses/30/lessons/43163
import java.util.Map;
import java.util.HashMap;

class Solution {
    
    Map<String, Integer> visited = new HashMap<>();
    
    private boolean isTargetExists(String[] words, String target) {
        for (String word : words) {
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean oneCharChanged(String each, String word) {
        int count = 0;
        for (int i = 0; i < each.length(); i++) {
            if (each.charAt(i) != word.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
    
    private void dfs(String word, int depth, String target, String[] words) {
        if (visited.getOrDefault(word, Integer.MAX_VALUE) < depth) {
            return;
        }
        visited.put(word, depth);
        if (word.equals(target)) {
            return;
        }
        for (String each : words) {
            if (!oneCharChanged(each, word)) {
                continue;
            }
            dfs(each, depth+1, target, words);
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        if (!isTargetExists(words, target)) {
            return 0;
        }
        dfs(begin, 0, target, words);
        return visited.get(target);
    }
}
