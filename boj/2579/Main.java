import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(in.readLine());
            if (i == 0) {
                dp[i][0] = number;
                continue;
            }
            if (i == 1) {
                dp[i][0] = number;
                dp[i][1] = dp[i-1][0] + number;
                continue;
            }
            dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + number;
            dp[i][1] = dp[i-1][0] + number;
        }
        System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
        in.close();
    }
}
