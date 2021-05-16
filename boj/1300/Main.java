import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long getCount(long number, int n) {
        long count = 0;
        for (int row = 1; row <= n; row++) {
            count += Math.min(number/row, n);
        }
        return count;
    }

    private static long solution(int n, int k) {
        long answer = -1;

        long left = 1;
        long right = k;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = getCount(mid, n);
            if (count < k) {
                left = mid + 1;
                continue;
            }
            answer = mid;
            right = mid - 1;
        }

        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int k = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n, k));
    }
}
