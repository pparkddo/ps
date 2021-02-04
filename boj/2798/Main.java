import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = nm[1];

        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int answer = 0;

        for (int i = 0; i < numbers.length-2; i++) {
            for (int j = i+1; j < numbers.length-1; j++) {
                for (int k = j+1; k < numbers.length; k++) {
                    int sum = numbers[i] + numbers[j] + numbers[k];
                    if (sum <= m) {
                        answer = Math.max(sum, answer);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
