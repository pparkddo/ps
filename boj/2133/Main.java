import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int number = 1; number <= n; number++) {
            if (number % 2 == 1) {
                continue;
            }
            dp[number] = dp[number-2] * 3;
            for (int step = 4; step <= number; step+=2) {
                dp[number] += dp[number-step] * 2;
            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n));
    }
}
