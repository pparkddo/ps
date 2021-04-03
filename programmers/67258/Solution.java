import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private int getDistinctCount(String[] gems) {
        return (int) Arrays.stream(gems).distinct().count();
    }

    private boolean isContainsAllGems(Map<String, Integer> counts, int distinctCount) {
        return counts.size() == distinctCount;
    }

    public int[] solution(String[] gems) {
        Map<String, Integer> counts = new HashMap<>();
        int distinctCount = getDistinctCount(gems);

        List<List<Integer>> answers = new ArrayList<>();
        int left = 0;
        int right = 0;
        counts.put(gems[left], 1);
        while (left < gems.length) {
            if (isContainsAllGems(counts, distinctCount)) {
                answers.add(List.of(left+1, right+1));
                int count = counts.get(gems[left]) - 1;
                if (count == 0) {
                    counts.remove(gems[left]);
                }
                else {
                    counts.put(gems[left], count);
                }
                left++;
                continue;
            }
            if (right >= gems.length-1) {
                break;
            }
            right++;
            counts.put(gems[right], counts.getOrDefault(gems[right], 0)+1);
        }

        Collections.sort(answers, new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int length1 = o1.get(1) - o1.get(0);
                int length2 = o2.get(1) - o2.get(0);
                if (length1 == length2) {
                    return Integer.compare(o1.get(0), o2.get(0));
                }
                return Integer.compare(length1, length2); 
            }
        });

        if (answers.isEmpty()) {
            return new int[] {left+1, right+1};
        }

        List<Integer> answer = answers.get(0);
        return new int[] {answer.get(0), answer.get(1)};
    }
}
