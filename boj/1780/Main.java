import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] paper;
    private static int minusOneCount = 0;
    private static int ZeroCount = 0;
    private static int OneCount = 0;

    private static int getColor(int row, int column, int size) {
        int color = paper[row][column];
        for (int i = row; i < row+size; i++) {
            for (int j = column; j < column+size; j++) {
                if (color != paper[i][j]) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return color;
    }
    
    private static void calculateCount(int row, int column, int size) {
        int color = getColor(row, column, size);
        if (color == -1) {
            minusOneCount += 1;
            return;
        }
        if (color == 0) {
            ZeroCount += 1;
            return;
        }
        if (color == 1) {
            OneCount += 1;
            return;
        }

        int nextSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nextRow = row + nextSize * i;
                int nextColumn = column + nextSize * j;
                calculateCount(nextRow, nextColumn, nextSize);
            }
        }
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

        System.out.println(minusOneCount);
        System.out.println(ZeroCount);
        System.out.println(OneCount);
    }
}
