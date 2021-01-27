import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isPrimeNumber(int x) {
        if (x == 1) {
            return false;
        }
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        in.close();

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            if (isPrimeNumber(i)) {
                primeNumbers.add(i);
            }
        }

        if (primeNumbers.isEmpty()) {
            System.out.println(-1);
            return;
        }

        System.out.println(primeNumbers.stream().mapToInt(Integer::intValue).sum());
        System.out.println(primeNumbers.stream().min(Integer::compare).get());
    }
}