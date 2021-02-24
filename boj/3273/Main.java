import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int x = Integer.parseInt(in.readLine());
        in.close();

        int answer = 0;
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < x) {
                left++;
            }
            else if (sum > x) {
                right--;
            }
            else {
                answer++;
                left++;
            }
        }
        System.out.println(answer);
    }
}
