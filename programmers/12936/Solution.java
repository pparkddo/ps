import java.util.ArrayList;
import java.util.List;

class Solution {

    private long getStep(int n) {
        long step = 1;
        for (int i = 1; i <= n; i++) {
            step *= i;
        }
        return step;
    }

    public int[] solution(int n, long k) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--;
        long step = getStep(n);
        int index = 0;
        int[] answer = new int[n];
        while (n > 0) {
            step /= n;
            answer[index++] = numbers.remove((int)(k / step));
            k %= step;
            n--;
        }

        return answer;
    }
}