import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private String removeOuterBracket(String s) {
        return s.substring(1, s.length()-1);
    }

    private List<List<String>> parseString(String s) {
        List<List<String>> subsets = new ArrayList<>();
        List<String> elements = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int index = 0;
        while (true) {
            if (index == s.length()-1) {
                elements.add(current.toString());
                subsets.add(List.copyOf(elements));
                break;
            }
            char c = s.charAt(index++);
            if (Character.isDigit(c)) {
                current.append(c);
                continue;
            }
            if (c == '}') {
                elements.add(current.toString());
                subsets.add(List.copyOf(elements));
                elements.clear();
            }
            if (c == ',' && current.length() > 0) {
                elements.add(current.toString());
            }
            current.delete(0, current.length());
        }
        return subsets;
    }

    private int[] getTuple(List<List<String>> subsets) {
        Map<String, Integer> positions = new HashMap<>();
        int position = 1;
        for (List<String> subset : subsets) {
            for (String each : subset) {
                if (positions.containsKey(each)) {
                    continue;
                }
                positions.put(each, position++);
            }
        }
        return positions.entrySet()
                        .stream()
                        .sorted((a, b)-> Integer.compare(a.getValue(), b.getValue()))
                        .mapToInt(each -> Integer.parseInt(each.getKey()))
                        .toArray();
    }

    public int[] solution(String s) {
        s = removeOuterBracket(s);
        List<List<String>> subsets = parseString(s);
        Collections.sort(subsets, (a, b) -> Integer.compare(a.size(), b.size()));
        return getTuple(subsets);
    }
}
