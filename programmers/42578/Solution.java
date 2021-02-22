import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> cs = new HashMap<>();
        for (String[] each : clothes) {
            String type = each[1];
            String name = each[0];
            List<String> l = cs.getOrDefault(type, new ArrayList<>());
            l.add(name);
            cs.put(type, l);
        }
        int answer = 1;
        for (String key : cs.keySet()) {
            answer *= (cs.get(key).size()+1);
        }
        return answer-1;
    }
}
