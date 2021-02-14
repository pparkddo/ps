import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p%q);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] diameters = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        StringBuilder answer = new StringBuilder();
        int first = diameters[0];
        for (int i = 1; i < n; i++) {
            int current = diameters[i];
            int d = gcd(first, current);
            answer.append(first/d).append("/").append(current/d).append("\n");;
        }
        System.out.println(answer.toString().stripTrailing());
    }
}
