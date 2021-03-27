import java.util.Arrays;

class Solution {

    private boolean canCrossOver(int[] stones, int k, int number) {
        int count = 0;
        for (int stone : stones) {
            if (count >= k) {
                break;
            }
            if (stone < number) {
                count++;
                continue;
            }
            count = 0;
        }
        return count < k;
    }

    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = Arrays.stream(stones).max().getAsInt();
        while (left <= right) {
            int mid = (left+right) / 2;
            if (canCrossOver(stones, k, mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
                continue;
            }
            right = mid - 1;
        }
        return answer;
    }
}