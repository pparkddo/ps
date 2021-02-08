import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int n;
    private static int[] numbers;
    private static int plusCount;
    private static int minusCount;
    private static int multiplyCount;
    private static int divideCount;
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    private static void dfs(int result, int depth) {
        if (depth == n) {
            min = Math.min(result, min);
            max = Math.max(result, max);
            return;
        }
        int number = numbers[depth];
        if (plusCount > 0) {
            plusCount -= 1;
            dfs(result+number, depth+1);
            plusCount += 1;
        }
        if (minusCount > 0) {
            minusCount -= 1;
            dfs(result-number, depth+1);
            minusCount += 1;
        }
        if (multiplyCount > 0) {
            multiplyCount -= 1;
            dfs(result*number, depth+1);
            multiplyCount += 1;
        }
        if (divideCount > 0) {
            divideCount -= 1;
            dfs(result/number, depth+1);
            divideCount += 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] counts = in.readLine().split(" ");
        plusCount = Integer.parseInt(counts[0]);
        minusCount = Integer.parseInt(counts[1]);
        multiplyCount = Integer.parseInt(counts[2]);
        divideCount = Integer.parseInt(counts[3]);
        in.close();
        dfs(numbers[0], 1);
        System.out.println(max);
        System.out.println(min);
    }
}
