import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Query {

    String format;
    int score;

    Query(String format, int score) {
        this.format = format;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.format + " " + this.score;
    }
}

class Solution {

    private List<String> getPowerSet(List<String> set) {
        List<String> powerSet = new ArrayList<>();
        int n = set.size();
        for (int i = 0; i < (1<<n); i++) {
            StringBuilder each = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    each.append(set.get(j));
                    continue;
                }
                each.append("-");
            }
            powerSet.add(each.toString());
        }
        return powerSet;
    }

    private List<String> infoToQueryFormats(String[] separated) {
        List<String> set = new ArrayList<>(Arrays.asList(separated));
        set.remove(set.size()-1);
        return getPowerSet(set);
    }

    private Query parseQuery(String query) {
        List<String> parsed = Arrays.stream(query.split(" and ")).collect(Collectors.toList());
        int lastIndex = parsed.size() - 1;
        String lastElement = parsed.get(lastIndex);
        String[] separatedLastElement = lastElement.split(" ");
        parsed.remove(lastIndex);
        parsed.add(separatedLastElement[0]);
        int score = Integer.parseInt(separatedLastElement[1]);
        StringBuilder concatenated = new StringBuilder();
        for (String each : parsed) {
            concatenated.append(each);
        }
        return new Query(concatenated.toString(), score);
    }

    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String each : info) {
            String[] separated = each.split(" ");
            int score = Integer.parseInt(separated[separated.length-1]);
            for (String queryFormat : infoToQueryFormats(separated)) {
                List<Integer> scores = map.getOrDefault(queryFormat, new ArrayList<>());
                scores.add(score);
                map.put(queryFormat, scores);
            }
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        int[] answers = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            Query q = parseQuery(query[i]);
            List<Integer> scores = map.getOrDefault(q.format, new ArrayList<>());
            int size = scores.size();
            int left = 0;
            int right = size - 1;
            int index = size;
            while (left <= right) {
                int mid = (left + right) / 2;
                int score = scores.get(mid);
                if (score >= q.score) {
                    index = mid;
                    right = mid - 1;
                    continue;
                }
                left = mid + 1;
            }
            answers[i] = size - index;
        }
        return answers;
    }
}
