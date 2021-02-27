import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private List<String> getSeparated(String s, int count) {
        List<String> separated = new ArrayList<>();
        count = count + 1;
        for (int index = 0; index < s.length(); index+=count) {
            if (index+count >= s.length()) {
                separated.add(s.substring(index, s.length()));
                break;
            }
            separated.add(s.substring(index, index+count));
        }
        return separated;
    }

    private String compress(List<String> separated) {
        StringBuilder s = new StringBuilder();
        String previous = null;
        int count = 1;
        for (String each : separated) {
            if (previous == null) {
                previous = each;
                continue;
            }
            if (previous.equals(each)) {
                count++;
                continue;
            }
            s.append(count == 1 ? "" : count).append(previous);
            previous = each;
            count = 1;
        }
        return s.append(count == 1 ? "" : count).append(previous).toString();
    }

    public int solution(String s) {
        Map<Integer, String> compressed = new HashMap<>();
        for (int count = 0; count <= s.length()/2; count++) {
            List<String> separated = getSeparated(s, count);
            compressed.put(count+1, compress(separated));
        }
        return compressed.values().stream().mapToInt(String::length).min().getAsInt();
    }
}