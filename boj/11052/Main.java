import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int solution(int n, int[] prices) {
        int[] dp = new int[n+1];
        for (int count = 1; count <= n; count++) {
            for (int c = 1; c <= count; c++) {
                dp[count] = Math.max(dp[count], dp[count-c]+prices[c]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] prices = new int[n+1];
        String[] values = in.readLine().split(" ");
        for (int count = 1; count <= n; count++) {
            prices[count] = Integer.parseInt(values[count-1]);
        }
        in.close();
        System.out.println(solution(n, prices));
    }
}
