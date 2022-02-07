import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    public int[] solution(String[] idList, String[] report, int k) {
        Map<String, Set<String>> reportResult = new HashMap<>();
        for (String each : report) {
            String[] separated = each.split(" ");
            String reportBy = separated[0];
            String reported = separated[1];
            Set<String> result = reportResult.getOrDefault(reported, new HashSet<>());
            result.add(reportBy);
            reportResult.put(reported, result);
        }

        Map<String, Integer> idResult = new HashMap<>();
        for (Map.Entry<String, Set<String>> each : reportResult.entrySet()) {
            int length = each.getValue().size();
            if (length >= k) {
                each.getValue().forEach(id -> idResult.put(id, idResult.getOrDefault(id, 0)+1));
            }
        }

        int[] answer = new int[idList.length];
        for (int index = 0; index < idList.length; index++) {
            String id = idList[index];
            answer[index] = idResult.getOrDefault(id, 0);
        }
        return answer;
    }
}