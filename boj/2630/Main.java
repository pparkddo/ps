import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] paper;
    private static int blueCount = 0;
    private static int whiteCount = 0;

    private static int getColoredCount(int row, int column, int size) {
        int coloredCount = 0;
        for (int i = row; i < row+size; i++) {
            for (int j = column; j < column+size; j++) {
                coloredCount += paper[i][j];
            }
        }
        return coloredCount;
    }
    
    private static void calculateCount(int row, int column, int size) {
        int coloredCount = getColoredCount(row, column, size);
        if (coloredCount == 0) {
            whiteCount += 1;
            return;
        }
        if (coloredCount == size * size) {
            blueCount += 1;
            return;
        }

        int nextSize = size / 2;
        calculateCount(row, column, nextSize);
        calculateCount(row, column+nextSize, nextSize);
        calculateCount(row+nextSize, column, nextSize);
        calculateCount(row+nextSize, column+nextSize, nextSize);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        calculateCount(0, 0, n);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }
}
