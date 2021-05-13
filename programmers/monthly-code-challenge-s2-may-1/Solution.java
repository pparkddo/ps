class Solution {
    
    private int getCount(int number) {
        int count = 0;
        for (int n = 1; n <= number; n++) {
            if (number % n == 0) {
                count++;
            }
        }
        return count;
    }

    public int solution(int left, int right) {
        int answer = 0;
        for (int number = left; number <= right; number++) {
            int count = getCount(number);
            if (count % 2 == 0) {
                answer += number;
                continue;
            }
            answer -= number;
        }
        return answer;
    }
}