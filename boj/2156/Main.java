import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] amounts = new int[n];
        for (int i = 0; i < n; i++) {
            amounts[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int amount = amounts[i];
            if (i == 0) {
                dp[i] = amount;
                continue;
            }
            if (i == 1) {
                dp[i] = amounts[0] + amount;
                continue;
            }
            if (i == 2) {
                dp[i] = Math.max(amounts[0], amounts[1]) + amount;
                dp[i] = Math.max(dp[i], dp[i-1]);
                continue;
            }
            dp[i] = Math.max(dp[i-2], dp[i-3]+amounts[i-1]) + amount;
            dp[i] = Math.max(dp[i], dp[i-1]);
        }
        System.out.println(dp[n-1]);
    }
}
