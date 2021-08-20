import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static int solution(int n, int[] weights) {
        int[] sorted = sortByReverseOrder(weights);
        int max = 0;
        for (int index = 0; index < sorted.length; index++) {
            int count = index + 1;
            int availableWeight = sorted[index] * count;
            max = Math.max(max, availableWeight);
        }
        return max;
    }

    private static int[] sortByReverseOrder(int[] weights) {
        return Arrays.stream(weights)
            .boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::valueOf).toArray();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        System.out.println(solution(n, weights));
    }
}
