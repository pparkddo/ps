import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int getMaximumSum(int[][] stickers) {
        int n = stickers[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];
        if (n > 1) {
            dp[0][1] = stickers[0][1] + dp[1][0];
            dp[1][1] = stickers[1][1] + dp[0][0];
        }
        for (int i = 2; i < n; i++) {
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i]; 
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i]; 
        }
        return Math.max(dp[0][n-1], dp[1][n-1]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(in.readLine());
            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                String[] scores = in.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(scores[j]);
                }
            }
            answers.append(getMaximumSum(stickers)).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
