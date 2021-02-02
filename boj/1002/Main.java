import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = input[0];
            int y1 = input[1];
            int r1 = input[2];
            int x2 = input[3];
            int y2 = input[4];
            int r2 = input[5];

            int sum = r1 + r2;
            int subtract = Math.abs(r1 - r2);
            double distance = getDistance(x1, y1, x2, y2);

            if (distance == 0 && r1 == r2) {
                System.out.println(-1);
                continue;
            }

            if (subtract < distance && distance < sum) {
                System.out.println(2);
                continue;
            }

            if (sum == distance || subtract == distance) {
                System.out.println(1);
                continue;
            }

            System.out.println(0);
        }
        in.close();
    }
}
