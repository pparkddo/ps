import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(in.readLine());

        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add((int)(n % 10));
            n /= 10;
        }
        System.out.println(
            digits
            .stream()
            .sorted(Comparator.reverseOrder())
            .map(each -> each.toString())
            .collect(Collectors.joining(""))
        );
        in.close();
    }
}
