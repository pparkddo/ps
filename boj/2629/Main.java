import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MAX_MARBLE_WEIGHT = 40_000;

    private static String solution(int weightCount, int[] weights, int marbleCount, int[] marbles) {
        boolean[][] dp = new boolean[weightCount+1][MAX_MARBLE_WEIGHT+1];
        for (int i = 0; i <= weightCount; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= weightCount; i++) {
            int weight = weights[i-1];
            for (int j = 1; j <= MAX_MARBLE_WEIGHT; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                    continue;
                }
                dp[i][j] |= dp[i-1][Math.abs(j-weight)];
                int sum = j + weight;
                if (sum < MAX_MARBLE_WEIGHT) {
                    dp[i][j] |= dp[i-1][sum];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int index = 0; index < marbleCount; index++) {
            answer.append(dp[weightCount][marbles[index]] ? "Y" : "N").append(" ");
        }

        return answer.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int weightCount = Integer.parseInt(in.readLine());
        int[] weights = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int marbleCount = Integer.parseInt(in.readLine());
        int[] marbles = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();
        System.out.println(solution(weightCount, weights, marbleCount, marbles));
    }
}
