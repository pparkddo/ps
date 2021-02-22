import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (i == 0) {
                    dp[i][j] = triangle[i][j];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                    continue;
                }
                if (j == triangle[i].length-1) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }
        
        return Arrays.stream(dp[triangle.length-1]).max().getAsInt();
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {7},
                    {3, 8},
                    {8, 1, 0},
                    {2, 7, 4, 4},
                    {4, 5, 2, 6, 5}
                }
            )
        );  // 30
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {7}
                }
            )
        );  // 7
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {7},
                    {1, 2}
                }
            )
        );  // 9
    }
}