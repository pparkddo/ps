import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        long[] dp = new long[MAX+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int number = 5; number <= MAX; number++) {
            dp[number] = dp[number-5] + dp[number-1];
        }
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            answers.append(dp[n]).append("\n");
        }
        System.out.println(answers.toString().stripTrailing());
        in.close();
    }
}
