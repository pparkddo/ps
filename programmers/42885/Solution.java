import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] > limit) {
                right--;
                answer++;
                continue;
            }
            left++;
            right--;
            answer++;
        }
        return answer;
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println(
            new Solution().solution(new int[] {1, 2, 2, 3, 1, 2}, 3)
        );
        System.out.println(
            new Solution().solution(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90}, 100)
        );
        System.out.println(
            new Solution().solution(new int[] {10, 40, 90, 90}, 100)
        );
    }
}