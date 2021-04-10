import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int solution(int n, int k, int[] coins) {
        int[] dp = new int[k+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int price = 1; price <= k; price++) {
                if (price < coin) {
                    continue;
                }
                dp[price] += dp[price-coin];
            }
        }
        return dp[k];
    }
    
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
        System.out.println(solution(n, k, coins));
    }
}
