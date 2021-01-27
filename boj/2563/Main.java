import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int LINE = 10;

    private static void attach(int[][] paper, int x, int y) {
        for (int i = y; i < y + LINE; i++) {
            for (int j = x; j < x + LINE; j++) {
                paper[i][j] = 1;
            }
        }
    }

    private static int getArea(int[][] paper) {
        int area = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                area = area + paper[i][j];
            }
        }
        return area;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(in.readLine());

        int[][] paper = new int[HEIGHT][WIDTH];

        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            attach(paper, x, y);
        }
        in.close();

        System.out.println(getArea(paper));
    }
}