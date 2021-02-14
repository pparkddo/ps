import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[MAX+1][MAX+1];
        dp[1][0] = dp[1][1] = 1;
        for (int n = 2; n <= MAX; n++) {
            for (int k = 0; k <= n; k++) {
                if (k == 0 || k == n) {
                    dp[n][k] = 1;
                    continue;
                }
                dp[n][k] = (dp[n-1][k-1] + dp[n-1][k]);
            }
        }

        int t = Integer.parseInt(in.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] nm = in.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            answer.append(dp[m][n]).append("\n");
        }
        in.close();
        System.out.println(answer.toString().stripTrailing());
    }
}
