import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int DIVISOR = 1_000_000_000;

    private static int solution(int n, int k) {
        int[][] dp = new int[n+1][k+1];

        Arrays.fill(dp[0], 1);
        for (int choice = 1; choice <= k; choice++) {
            int number = 1;
            int count = choice;
            dp[number][choice] = count;
        }

        for (int number = 2; number <= n; number++) {
            dp[number][1] = 1;
            for (int choice = 2; choice <= k; choice++) {
                for (int rest = 0; rest <= number; rest++) {
                    dp[number][choice] = (dp[number][choice] + dp[rest][choice-1]) % DIVISOR;
                }
            }
        }

        return dp[n][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        in.close();
        System.out.println(solution(n, k));
    }
}
