import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] dp;

    private static int getSum(int row, int column) {
        if (column < 0) {
            return 0;
        }
        if (column > row) {
            return 0;
        }
        return dp[row][column];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        dp = new int[n][n];
        for (int row = 0; row < n; row++) {
            int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (row == 0) {
                dp[row][row] = numbers[0];
                continue;
            }
            for (int column = 0; column < numbers.length; column++) {
                dp[row][column] = Math.max(getSum(row-1, column-1), getSum(row-1, column)) + numbers[column];
            }
        }
        System.out.println(Arrays.stream(dp[n-1]).max().getAsInt());
        in.close();
    }
}
