class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int sum = 0;

        for (int index = 0; index < signs.length; index++) {
            int number = absolutes[index];
            if (signs[index]) {
                sum += number;
                continue;
            }
            sum -= number;
        }

        return sum;
    }
}