import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    private static final int EMPTY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        in.close();
        
        int[] numbers = IntStream.range(2, n+1).toArray();

        int index = -1;
        int answer = 0;
        int count = 0;
        while (true) {
            index = index + 1;
            int number = numbers[index];

            if (index == n) {
                break;
            }
            if (number == EMPTY) {
                continue;
            }

            for (int i = 0; i < n/number; i++) {
                if (numbers[index+i*number] == EMPTY) {
                    continue;
                }
                count = count + 1;
                if (count == k) {
                    answer = numbers[index+i*number];
                    break;
                }
                numbers[index+i*number] = EMPTY;
            }

            if (answer != 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}