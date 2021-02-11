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
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (i == 0) {
                dp[i] = number;
                continue;
            }
            int sum = Math.max(dp[i-1], 0) + number;
            if (sum < 0) {
                dp[i] = number;
                continue;
            }
            dp[i] = sum;
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
