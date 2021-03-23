import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknames = new HashMap<>();
        for (String each : record) {
            String[] separated = each.split(" ");
            String command = separated[0];
            String id = separated[1];
            if (command.equals("Enter") || command.equals("Change")) {
                nicknames.put(id, separated[2]);
            }
        }

        List<String> answer = new ArrayList<>();
        for (String each : record) {
            String[] separated = each.split(" ");
            String command = separated[0];
            String id = separated[1];
            if (command.equals("Enter")) {
                answer.add(nicknames.get(id) + "님이 들어왔습니다.");
            }
            else if (command.equals("Leave")) {
                answer.add(nicknames.get(id) + "님이 나갔습니다.");
            }
        }

        return answer.stream().toArray(String[]::new);
    }
}
