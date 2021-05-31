import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int solution(int n, int m, int[] memories, int[] costs) {
        int sum = Arrays.stream(costs).sum();

        int[][] dp = new int[n+1][sum+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - costs[i] < 0) {
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-costs[i]]+memories[i]);
            }
        }

        for (int index = 0; index < dp[0].length; index++) {
            if (dp[dp.length-1][index] >= m) {
                return index;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] ms = in.readLine().split(" ");
        int[] memories = new int[n+1];
        for (int index = 1; index <= n; index++) {
            memories[index] = Integer.parseInt(ms[index-1]);
        }

        String[] cs = in.readLine().split(" ");
        int[] costs = new int[n+1];
        for (int index = 1; index <= n; index++) {
            costs[index] = Integer.parseInt(cs[index-1]);
        }
        in.close();
        System.out.println(solution(n, m, memories, costs));
    }
}
