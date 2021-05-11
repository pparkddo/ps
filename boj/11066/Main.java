import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int NOT_VISITED = -1;

    private static int getRangeSum(int start, int end, int[] sizes, int[][] dp, int[] prefixSum) {
        if (dp[start][end] != NOT_VISITED) {
            return dp[start][end];
        }
        if (start == end) {
            return 0;
        }
        int sum = getSum(start, end, prefixSum);
        for (int index = start; index < end; index++) {
            int rangeSum = getRangeSum(start, index, sizes, dp, prefixSum) + getRangeSum(index+1, end, sizes, dp, prefixSum) + sum;
            if (dp[start][end] == -1 || dp[start][end] > rangeSum) {
                dp[start][end] = rangeSum;
            }
        }
        return dp[start][end];
    }

    private static int getSum(int start, int end, int[] prefixSum) {
        return prefixSum[end] - prefixSum[start-1];
    }

    private static int[] getPrefixSum(int k, int[] sizes) {
        int[] prefixSum = new int[k+1];
        for (int index = 1; index <= k; index++) {
            prefixSum[index] = prefixSum[index-1] + sizes[index];
        }
        return prefixSum;
    }

    private static int solution(int k, int[] sizes) {
        int[][] dp = new int[k+1][k+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }
        int[] prefixSum = getPrefixSum(k, sizes);
        return getRangeSum(1, k, sizes, dp, prefixSum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int k = Integer.parseInt(in.readLine());
            int[] sizes = new int[k+1];
            String[] each = in.readLine().split(" ");
            for (int index = 1; index <= k; index++) {
                sizes[index] = Integer.parseInt(each[index-1]);
            }
            answers.append(solution(k, sizes)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
