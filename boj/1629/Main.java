import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long pow(int a, int b, int c) {
        if (b == 0) {
            return 1;
        }

        long number = pow(a, b/2, c);
        long current = number * number % c;

        if (b % 2 == 0) {
            return current;
        }
        else {
            return a * current % c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] abc = in.readLine().split(" ");
        in.close();

        int a = Integer.parseInt(abc[0]);
        int b = Integer.parseInt(abc[1]);
        int c = Integer.parseInt(abc[2]);

        System.out.println(pow(a, b, c));
    }
}
