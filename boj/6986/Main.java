import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(Double.parseDouble(in.readLine()));
        }
        List<Double> sorted = numbers.stream().sorted().collect(Collectors.toList());
        in.close();

        System.out.printf("%.2f%n", getTrimmedMean(sorted, k));
        System.out.printf("%.2f%n", getAdjustedMean(sorted, k));
    }

    private static double getTrimmedMean(List<Double> numbers, int k) {
        double sum = 0;
        for (int index = k; index < numbers.size()-k; index++) {
            sum += numbers.get(index);
        }
        return sum / (numbers.size()-(k*2));
    }

    private static double getAdjustedMean(List<Double> numbers, int k) {
        double sum = 0;
        for (int index = k; index < numbers.size()-k; index++) {
            sum += numbers.get(index);
        }
        sum += numbers.get(k) * k;
        sum += numbers.get(numbers.size()-k-1) * k;
        return sum / numbers.size();
    }

    private static double round(double number, int digit) {
        double adjust = Math.pow(10, digit);
        return Math.round(number * adjust) / adjust;
    }
}
