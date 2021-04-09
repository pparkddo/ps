import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int DIVISOR = 1_000_000;

    private static int solution(String secret) {
        int[] dp = new int[secret.length()+1];
        int[] characters = new int[secret.length()+1];

        for (int index = 0; index < secret.length(); index++) {
            characters[index+1] = secret.charAt(index) - '0';
        }

        dp[0] = 1;

        for (int index = 1; index <= secret.length(); index++) {
            if (characters[index] != 0) {
                dp[index] = (dp[index-1] + dp[index]) % DIVISOR;
            }
            int value = characters[index] + characters[index-1] * 10;
            if (10 <= value && value <= 26) {
                dp[index] = (dp[index-2] + dp[index]) % DIVISOR;
            }
        }

        return dp[secret.length()] % DIVISOR;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String secret = in.readLine();
        in.close();

        System.out.println(solution(secret));
    }
}
