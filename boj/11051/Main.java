import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int DIVIDER = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        in.close();

        int[][] dp = new int[n+1][n+1];
        
        dp[1][0] = dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % DIVIDER;
            }
        }

        System.out.println(dp[n][k]);
    }
}
