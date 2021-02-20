import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> check = new HashMap<>();
        for (String p : participant) {
            check.put(p, check.getOrDefault(p, 0)+1);
        }
        for (String c : completion) {
            check.put(c, check.get(c)-1);
        }
        String answer = "";
        for (String p : participant) {
            if (check.get(p) == 1) {
                answer = p;
            }
        }
        return answer;
    }
}