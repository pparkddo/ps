import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        int answer = 0;
        for (int i = n-1; i >= 0; i--) {
            if (k == 0) {
                break;
            }
            int coin = coins[i];
            answer += k / coin;
            k = k % coin;
        }
        System.out.println(answer);
    }
}
