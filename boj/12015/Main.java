import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int getReplaceIndex(int[] lis, int lisIndex, int number) {
        int left = 0;
        int right = lisIndex;
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < number) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int[] lis = new int[n];
        int lisIndex = 0;
        for (int index = 0; index < numbers.length; index++) {
            int number = numbers[index];
            if (index == 0) {
                lis[lisIndex] = number;
                continue;
            }
            if (number > lis[lisIndex]) {
                lis[++lisIndex] = number;
                continue;
            }
            lis[getReplaceIndex(lis, lisIndex, number)] = number;
        }

        System.out.println(lisIndex+1);
    }
}
