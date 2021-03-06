import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            if (i == 0) {
                dp[i] = number;
                continue;
            }
            if (i == 1) {
                dp[i] = dp[i-1] + number;
                continue;
            }
            if (i == 2) {
                dp[i] = Math.max(numbers[0], numbers[1]) + number;
                continue;
            }
            dp[i] = Math.max(dp[i-3]+numbers[i-1], dp[i-2]) + number;
        }
        System.out.println(dp[n-1]);
        in.close();
    }
}
