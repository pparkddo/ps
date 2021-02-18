import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static long getCount(int[] lines, long length) {
        int count = 0;
        for (int line : lines) {
            count += line / length;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] kn = in.readLine().split(" ");
        int k = Integer.parseInt(kn[0]);
        int n = Integer.parseInt(kn[1]);
        int[] lines = new int[k];
        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        long answer = 0;
        long left = 1;
        long right = Arrays.stream(lines).max().getAsInt();
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = getCount(lines, mid);
            if (count >= n) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        System.out.println(answer);
    }
}
