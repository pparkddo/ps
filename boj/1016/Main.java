import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long min = input[0];
        long max = input[1];
        in.close();

        int gap = Long.valueOf(max-min).intValue();
        boolean[] answers = new boolean[gap+1];

        for (long i = 2; (i*i) <= max; i++) {
            long square = (long) Math.pow(i, 2);
            long startNumber = min % square == 0 ? min : square * (min / square + 1);
            for (long number = startNumber; number <= max; number+=square) {
                int index = Long.valueOf(number-min).intValue();
                answers[index] = true;
            }
        }

        int count = 0;
        for (boolean b : answers) {
            if (!b) {
                count += 1;
            }
        }
        System.out.println(count);
    }
}
