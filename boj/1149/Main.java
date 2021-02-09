import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            int[] costs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int costIndex = 0; costIndex < 3; costIndex++) {
                int cost = costs[costIndex];
                if (i == 0) {
                    dp[i][costIndex] = cost;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int index = 0; index < costs.length; index++) {
                    if (index == costIndex) {
                        continue;
                    }
                    min = Math.min(min, dp[i-1][index]);
                }
                dp[i][costIndex] = min + cost;
            }
        }
        in.close();
        System.out.println(Arrays.stream(dp[n-1]).min().getAsInt());
    }
}
