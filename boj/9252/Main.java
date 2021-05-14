import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String getLcs(int[][] dp, String s, String t) {
        StringBuilder lcs = new StringBuilder();
        int i = s.length();
        int j = t.length();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j]) {
                i--;
            }
            else if (dp[i][j] == dp[i][j-1]) {
                j--;
            }
            else {
                lcs.append(s.charAt(i-1));
                i--;
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    private static String solution(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return new StringBuilder()
                    .append(dp[s.length()][t.length()])
                    .append("\n")
                    .append(getLcs(dp, s, t)).toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String t = in.readLine();
        in.close();
        System.out.println(solution(s, t));
    }
}
