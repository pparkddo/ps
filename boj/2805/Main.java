import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static long getAvailableAmount(int[] trees, long length) {
        long amount = 0;
        for (int tree : trees) {
            amount += Math.max(tree-length, 0);
        }
        return amount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int m = Integer.parseInt(nm[1]);
        int[] trees = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        long answer = 0;
        long left = 0;
        long right = Arrays.stream(trees).max().getAsInt();
        while(left <= right) {
            long mid = (right+left) / 2;
            long amount = getAvailableAmount(trees, mid);
            if (amount >= m) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        System.out.println(answer);
    }
}
