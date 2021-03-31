import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        int[] sortedCoins = Arrays.stream(coins).distinct().sorted().toArray();
        int MAX_VALUE = Integer.MAX_VALUE;

        int[] dp = new int[k+1];
        Arrays.fill(dp, MAX_VALUE);
        dp[0] = 0;
        for (int coinIndex = 0; coinIndex < sortedCoins.length; coinIndex++) {
            int coin = sortedCoins[coinIndex];
            for (int number = coin; number <= k; number++) {
                if (dp[number-coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[number] = Math.min(dp[number], dp[number-coin]+1);
            }
        }

        System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
    }
}
