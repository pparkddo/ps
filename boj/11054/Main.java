import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] a = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int[][] dp = new int[2][n];
        for (int i = 0; i < a.length; i++) {
            int maxLength = 0;
            int reverseMaxLength = 0;
            for (int j = i-1; j >= 0; j--) {
                if (a[j] < a[i]) {
                    maxLength = Math.max(maxLength, dp[0][j]);
                }
                if (a[n-j-1] < a[n-i-1]) {
                    reverseMaxLength = Math.max(reverseMaxLength, dp[1][n-j-1]);
                }
            }
            dp[0][i] = maxLength + 1;
            dp[1][n-i-1] = reverseMaxLength + 1;
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            answer = Math.max(answer, dp[0][i]+dp[1][i]);
        }
        System.out.println(answer-1);
    }
}
