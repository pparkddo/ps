import java.util.Arrays;

class Solution {

    public int solution(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        int sumOfAll = 45;
        return sumOfAll - sum;
    }
}