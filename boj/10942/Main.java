import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] dp;
    private static final int NOT_VISITED = -1;
    private static final int IS_PALINDROME = 1;
    private static final int IS_NOT_PALINDROME = 0;

    private static int solution(int[] numbers, int s, int e) {
        return dfs(numbers, s, e);
    }

    private static int dfs(int[] numbers, int s, int e) {
        if (dp[s][e] != NOT_VISITED) {
            return dp[s][e];
        }
        if (s == e) {
            return dp[s][e] = IS_PALINDROME;
        }
        if (s == e-1) {
            return dp[s][e] = numbers[s] == numbers[e] ? IS_PALINDROME : IS_NOT_PALINDROME;
        }
        if (numbers[s] != numbers[e]) {
            return dp[s][e] = IS_NOT_PALINDROME;
        }
        return dp[s][e] = dfs(numbers, s+1, e-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }
        String[] numbersInString = in.readLine().split(" ");
        int[] numbers = new int[n+1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(numbersInString[i-1]);
        }
        int m = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] se = in.readLine().split(" ");
            int s = Integer.parseInt(se[0]);
            int e = Integer.parseInt(se[1]);
            answers.append(solution(numbers, s, e)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
