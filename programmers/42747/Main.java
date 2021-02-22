import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int answer = 0;
        for (int index = 0; index < citations.length; index++) {
            int h = citations[index];
            int count = citations.length - index;
            if (count <= h) {
                answer = count;
                break;
            }
        }
        return answer;
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(
            new Solution().solution(new int[] {3, 0, 6, 1, 5})  // 3
        );
        System.out.println(
            new Solution().solution(new int[] {0, 1, 312, 451, 33})  // 3
        );
        System.out.println(
            new Solution().solution(new int[] {42, 47, 32, 28, 24, 22, 17, 15, 10, 10, 8})  // 10
        );
        System.out.println(
            new Solution().solution(new int[] {22, 42})  // 2
        );
        System.out.println(
            new Solution().solution(new int[] {22})  // 1
        );
        System.out.println(
            new Solution().solution(new int[] {0, 0, 0})  // 0
        );
    }
}
