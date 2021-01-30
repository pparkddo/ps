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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[] locations = new int[n];
        for (int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        int gcd = 0;
        int[] distances = new int[n-1];
        for (int i = 0; i < locations.length-1; i++) {
            int current = locations[i+1] - locations[i];
            distances[i] = current;
            if (i == 0) {
                gcd = current;
                continue;
            }
            gcd = getGcd(gcd, current);
        }

        int answer = 0;
        for (int distance : distances) {
            answer = answer + (distance/gcd-1);
        }
        System.out.println(answer);
    }
}