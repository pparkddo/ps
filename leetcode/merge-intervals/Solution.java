import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> answer = new ArrayList<>();
        int start = -1;
        int end = -1;
        for (int[] each : intervals) {
            if (start == -1) {
                start = each[0];
                end = each[1];
                continue;
            }
            if (end >= each[0]) {
                end = Math.max(end, each[1]);
                continue;
            }
            answer.add(new int[] {start, end});
            start = each[0];
            end = each[1];
        }
        answer.add(new int[] {start, end});

        return answer.toArray(new int[answer.size()][2]);
    }
}
