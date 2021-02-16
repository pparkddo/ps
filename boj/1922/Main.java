import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int WHITE = 0;
    private static final int BLACK = 1;
    private static int[][] photo;
    private static StringBuilder answer = new StringBuilder();

    private static int getColorCount(int row, int column, int size) { 
        int colorCount = 0;
        for (int i = row; i < row+size; i++) {
            for (int j = column; j < column+size; j++) {
                colorCount += photo[i][j];
            }
        }
        return colorCount;
    }

    private static void compress(int row, int column, int size) {
        int colorCount = getColorCount(row, column, size);
        if (colorCount == 0) {
            answer.append(WHITE);
            return;
        }
        if (colorCount == size*size) {
            answer.append(BLACK);
            return;
        }
        int nextSize = size / 2;
        answer.append("(");
        compress(row, column, nextSize);
        compress(row, column+nextSize, nextSize);
        compress(row+nextSize, column, nextSize);
        compress(row+nextSize, column+nextSize, nextSize);
        answer.append(")");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        photo = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split("");
            for (int j = 0; j < n; j++) {
                photo[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        compress(0, 0, n);

        System.out.println(answer);
    }
}
