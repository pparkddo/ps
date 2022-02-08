import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] area = new boolean[MAX][MAX];
        for (int index = 0; index < 4; index++) {
            String[] each = in.readLine().split(" ");
            int x1 = Integer.parseInt(each[0]);
            int y1 = Integer.parseInt(each[1]);
            int x2 = Integer.parseInt(each[2]);
            int y2 = Integer.parseInt(each[3]);
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    area[y][x] = true;
                }
            }
        }

        in.close();

        int answer = 0;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (area[i][j]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}