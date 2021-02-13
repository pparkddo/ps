import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] weights = new int[n+1];
        int[] values = new int[n+1];
        for (int i = 1; i <= n; i++) {
            String[] wv = in.readLine().split(" ");
            weights[i] = Integer.parseInt(wv[0]);
            values[i] = Integer.parseInt(wv[1]);
        }
        in.close();

        int[][] dp = new int[n+1][k+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - weights[i] < 0) {
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]]+values[i]);
            }
        }

        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }
}
