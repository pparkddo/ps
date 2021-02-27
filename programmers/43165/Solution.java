class Solution {

    int[] numbers;
    int target;
    int answer = 0;

    private void dfs(int sum, int depth) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        int number = numbers[depth];
        dfs(sum+number, depth+1);
        dfs(sum-number, depth+1);
    }

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        dfs(0, 0);
        return answer;
    }
}
