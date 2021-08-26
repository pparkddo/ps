import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int solution(int n, int[] numbers, int m) {
        int maxNumber = getMaxNumber(numbers);

        int left = 0;
        int right = maxNumber;
        int answer = 0;
        while (left <= right) {
            int mid = (left+right) / 2;
            int sum = getSum(numbers, mid);
            if (sum <= m) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static int getSum(int[] numbers, int budget) {
        int sum = 0;
        for (int number : numbers) {
            if (number <= budget) {
                sum += number;
            } else {
                sum += budget;
            }
        }
        return sum;
    }

    private static int getMaxNumber(int[] numbers) {
        return Arrays.stream(numbers).max().getAsInt();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n, numbers, m));
    }
}
