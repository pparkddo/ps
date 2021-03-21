import java.util.Arrays;

class Solution {

    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int camera = Integer.MIN_VALUE;
        int count = 0;
        for (int[] route : routes) {
            if (camera < route[0]) {
                camera = route[1];
                count++;
            }
        }
        return count;
    }
}
