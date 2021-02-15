import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int getFactorialFactorCount(int number, int factor) {
        int count = 0;
        while (number >= factor) {
            count += number / factor;
            number /= factor;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        in.close();

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int answer = Math.min(
            getFactorialFactorCount(n, 2) - getFactorialFactorCount(n-m, 2) - getFactorialFactorCount(m, 2),
            getFactorialFactorCount(n, 5) - getFactorialFactorCount(n-m, 5) - getFactorialFactorCount(m, 5)
        );
        
        System.out.println(answer);
    }
}
