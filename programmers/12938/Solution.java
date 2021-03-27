import java.util.Arrays;

class Solution {

    public int[] solution(int n, int s) {
        int baseNumber =  s / n;
        if (baseNumber == 0) {
            return new int[] {-1};
        }
        int[] answer = new int[n];
        Arrays.fill(answer, baseNumber);
        int remainder = s % n;
        int index = n - 1;
        for (int i = 0; i < remainder; i++) {
            answer[index] += 1;
            index--;
        }
        return answer;
    }
}
