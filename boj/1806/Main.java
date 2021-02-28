import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ns = in.readLine().split(" ");
        int s = Integer.parseInt(ns[1]);
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();
        
        int left = 0;
        int right = 1;
        int sum = numbers[left] + numbers[right];
        int min = Integer.MAX_VALUE;
        while (left < numbers.length) {
            if (sum < s) {
                if (right == numbers.length-1) {
                    break;
                }
                sum = sum + numbers[++right];
                continue;
            }
            min = Math.min(min, right-left+1);
            sum = sum - numbers[left++];
        }

        int answer = min == Integer.MAX_VALUE ? 0 : min;
        System.out.println(answer);
    }
}
