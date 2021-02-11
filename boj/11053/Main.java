import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int[] dp = new int[n];
        for (int index = 0; index < n; index++) {
            int number = numbers[index];
            int maxLength = 0;
            for (int i = index-1; i >= 0; i--) {
                if (numbers[i] < number) {
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
            dp[index] = maxLength + 1;
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
