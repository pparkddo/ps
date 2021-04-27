import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long[] dp;

    private static long fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = fibonacci(n-1) + fibonacci(n-2);
    }

    private static long solution(int n) {
        dp = new long[n+1];
        return fibonacci(n);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n));
    }
}
