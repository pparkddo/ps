import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();

        int[] dp = new int[n+1];
        for (int number = 1; number <= n; number++) {
            dp[number] = number;
            for (int each = 1; (each*each) <= number; each++) {
                int squared = each * each;
                dp[number] = Math.min(dp[number], dp[number-squared]+1);
            }
        }

        System.out.println(dp[n]);
    }
}
