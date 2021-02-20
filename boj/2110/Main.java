import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int getCount(int[] homes, long distance) {
        int count = 1;
        int previous = 0;
        for (int i = 0; i < homes.length; i++) {
            if (i == 0) {
                previous = homes[i];
                continue;
            }
            int gap = homes[i] - previous;
            if (gap >= distance) {
                previous = homes[i];
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nc = in.readLine().split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]);
        int[] homes = new int[n];
        for (int i = 0; i < n; i++) {
            homes[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        Arrays.sort(homes);

        long answer = 0;
        long left = 0;
        long right = homes[homes.length-1];
        while (left <= right) {
            long mid = (left+right) / 2;
            int count = getCount(homes, mid);
            if (count >= c) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        System.out.println(answer);
    }
}
