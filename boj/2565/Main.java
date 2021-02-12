import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] lines = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            String[] input = in.readLine().split(" ");
            lines[i][0] = Integer.parseInt(input[0]);
            lines[i][1] = Integer.parseInt(input[1]);
        }
        in.close();

        Arrays.sort(lines, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
			}
        });

        int[] dp = new int[n+1];
        for (int number = 1; number <= n; number++) {
            int maxLength = 0;
            for (int i = number-1; i > 0; i--) {
                if (lines[i][1] < lines[number][1]) {
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
            dp[number] = maxLength + 1;
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(n-answer);
    }
}
