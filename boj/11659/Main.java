import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] dp = new int[n];
        dp[0] = numbers[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1] + numbers[i];
        }

        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int start = Integer.parseInt(each[0]) - 1;
            int end = Integer.parseInt(each[1]) - 1;
            int answer = dp[end] - dp[start] + numbers[start];
            answers.append(answer).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
