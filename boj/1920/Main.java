import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int binarySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = (left+right) / 2;
            if (numbers[mid] < target) {
                left = mid + 1;
            }
            else if (numbers[mid] > target) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] a = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        in.readLine();
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        StringBuilder answer = new StringBuilder();
        for (int number : numbers) {
            answer.append(binarySearch(a, number) != -1 ? 1 : 0).append("\n");
        }
        System.out.println(answer.toString().stripTrailing());
    }
}
