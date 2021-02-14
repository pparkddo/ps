import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int factorial(int number, int stop) {
        if (number == stop) {
            return stop;
        }
        return number * factorial(number-1, stop);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        in.close();

        if (k == 0) {
            System.out.println(1);
            return;
        }

        System.out.println(factorial(n, n-k+1) / factorial(k, 1));
    }
}
