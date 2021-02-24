import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] values = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        in.close();

        int left = 0;
        int right = values.length - 1;
        int minimum = Integer.MAX_VALUE;
        int leftAnswer = 0;
        int rightAnswer = 0;
        while (left < right) {
            int sum = values[left] + values[right];
            if (Math.abs(sum) < minimum) {
                minimum = Math.abs(sum);
                leftAnswer = values[left];
                rightAnswer = values[right];
            }
            if (sum < 0) {
                left++;
            }
            else if (sum > 0) {
                right--;
            }
            else {
                leftAnswer = values[left];
                rightAnswer = values[right];
                break;
            }
        }
        System.out.println(String.format("%d %d", leftAnswer, rightAnswer));
    }
}
