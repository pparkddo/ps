import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][][] dp;

    private static int getResult(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }
        if (a > 20 || b > 20 || c > 20) {
            return dp[20][20][20];
        }
        if (a < b && b < c) {
            return dp[a][b][c-1] + dp[a][b-1][c-1] - dp[a][b-1][c];
        }
        else {
            return dp[a-1][b][c] + dp[a-1][b-1][c] + dp[a-1][b][c-1] - dp[a-1][b-1][c-1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[21][21][21];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < dp.length; k++) {
                    dp[i][j][k] = getResult(i, j, k);
                }
            }
        }
        StringBuilder answers = new StringBuilder();
        while (true) {
            String[] numbers = in.readLine().split(" ");
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            int c = Integer.parseInt(numbers[2]);
            if (a == -1 && b == -1 && c == -1) {
                break;
            }
            answers
            .append("w(")
            .append(a)
            .append(", ")
            .append(b)
            .append(", ")
            .append(c)
            .append(") = ")
            .append(getResult(a, b, c))
            .append("\n");
        }
        System.out.println(answers.toString().stripTrailing());
        in.close();
    }
}
