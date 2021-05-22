import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MAX = 2_001;

    private static int convertToIndex(int number) {
        return number + MAX / 2;
    }

    private static int convertToNumber(int index) {
        return index - MAX / 2;
    }

    private static String solution(int n, int[] numbers) {
        int[] sorted = new int[MAX];
        for (int number : numbers) {
            sorted[convertToIndex(number)]++;
        }
        StringBuilder answer = new StringBuilder();
        for (int index = 0; index < sorted.length; index++) {
            if (sorted[index] == 0) {
                continue;
            }
            answer.append(convertToNumber(index)).append(" ");
        }
        return answer.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();
        System.out.println(solution(n, numbers));
    }
}
