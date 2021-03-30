import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int getGcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return getGcd(q, p % q);
    }

    public static int getLcm(int p, int q) {
        return p * q / getGcd(p, q);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            String[] mnxy = in.readLine().split(" ");
            int m = Integer.parseInt(mnxy[0]);
            int n = Integer.parseInt(mnxy[1]);
            int x = Integer.parseInt(mnxy[2]);
            int y = Integer.parseInt(mnxy[3]);
            int lcm = getLcm(m, n);
            x = x == m ? 0 : x;
            y = y == n ? 0 : y;
            int maxFactor = lcm / m;
            int answer = -1;
            for (int i = 0; i <= maxFactor; i++) {
                int year = m * i + x;
                if (year % n == y) {
                    answer = year;
                    break;
                }
            }
            answers.append(answer == 0 ? lcm : answer).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
