import java.util.Arrays;

class Solution {

    private long getImmigrationCount(long minutes, int[] times) {
        long count = 0;
        for (int time : times) {
            count += minutes / time;
        }
        return count;
    }

    public long solution(int n, int[] times) {
        int slowest = Arrays.stream(times).max().getAsInt();

        long answer = Long.MAX_VALUE;
        long left = 1;
        long right = (long) slowest * n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long immigrationCount = getImmigrationCount(mid, times);
            if (immigrationCount < n) {
                left = mid + 1;
                continue;
            }
            answer = Math.min(answer, mid);
            right = mid - 1;
        }

        return answer;
    }
}
