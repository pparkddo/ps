import java.util.ArrayList;
import java.util.List;

class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> temp = new ArrayList<>();

        int i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            temp.add(intervals[i++]);
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            merge(newInterval, intervals[i]);
            i++;
        }

        temp.add(newInterval);

        while (i < intervals.length) {
            temp.add(intervals[i++]);
        }

        int[][] answer = new int[temp.size()][2];
        for (int index = 0; index < temp.size(); index++) {
            answer[index] = temp.get(index);
        }
        return answer;
    }

    private void merge(int[] a, int[] b) {
        a[0] = Math.min(a[0], b[0]);
        a[1] = Math.max(a[1], b[1]);
    }
}