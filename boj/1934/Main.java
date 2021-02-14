import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }

    public static int lcm(int p, int q) {
        return p * q / gcd(p, q);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            String[] each = in.readLine().split(" ");
            int answer = lcm(Integer.parseInt(each[0]), Integer.parseInt(each[1]));
            System.out.println(answer);
        }
        in.close();
    }
}
