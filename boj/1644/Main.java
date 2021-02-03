import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> getPrimeNumbers(int n) {
        boolean[] eratos = new boolean[n+1];

        for (int i = 2; (i * i) < eratos.length; i++) {
            for (int number = (i*i); number < eratos.length; number += i) {
                eratos[number] = true;
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();

        for (int number = 2; number < eratos.length; number++) {
            if (!eratos[number]) {
                primeNumbers.add(number);
            }
        }

        return primeNumbers;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        if (n == 2) {
            System.out.println(1);
            return;
        }

        List<Integer> primeNumbers = getPrimeNumbers(n);
        int start = 0;
        int end = 1;
        int sum = primeNumbers.get(start) + primeNumbers.get(end);
        int answer = 0;

        while (start != end) {
            if (sum > n) {
                sum = sum - primeNumbers.get(start);
                start = start + 1;
                continue;
            }
            if (sum == n) {
                answer = answer + 1;
                sum = sum - primeNumbers.get(start);
                start = start + 1;
            }
            if (end < primeNumbers.size()) {
                end = end + 1;
                sum = sum + primeNumbers.get(end);
            }
        }

        if (primeNumbers.get(primeNumbers.size()-1) == n) {
            answer = answer + 1;
        }

        System.out.println(answer);
    }
}
