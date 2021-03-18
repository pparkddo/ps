import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean isPossible(long n, long m, long count) {
        long rest = n - 5 * count;
        return rest + m >= 7 * count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] each = in.readLine().split(" ");
            long n = Long.parseLong(each[0]);
            long m = Long.parseLong(each[1]);
            long left = 0;
            long right = n / 5;
            long answer = 0;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                if (isPossible(n, m, mid)) {
                    answer = mid;
                    left = mid + 1;
                    continue;
                }
                right = mid - 1;
            }
            answers.append(answer).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
