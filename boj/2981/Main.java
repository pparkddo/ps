import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }

    private static Set<Integer> getFactors(int number) {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(number);
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);
                factors.add(number/i);
            }
        }
        return factors;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        Arrays.sort(numbers);

        int p = numbers[1] - numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int q = numbers[i] - numbers[i-1];
            p = gcd(p, q);
        }

        Set<Integer> factors = getFactors(p);
        factors.remove(1);
        String answer = factors.stream().sorted().map(each -> each.toString()).collect(Collectors.joining(" "));
        System.out.println(answer);
    }
}
