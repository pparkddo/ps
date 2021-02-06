import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static String toggleColor(String color) {
        return color == "B" ? "W" : "B";
    }

    private static int getCount(String[][] board, int rowIndex, int columnIndex, String initialColor) {
        int count = 0;

        String color = toggleColor(initialColor);

        for (int i = rowIndex; i < rowIndex+8; i++) {
            color = toggleColor(color);
            for (int j = columnIndex; j < columnIndex+8; j++) {
                color = toggleColor(color);
                if (board[i][j].equals(color)) {
                    continue;
                }
                count = count + 1;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];

        String[][] board = new String[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = in.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = row[j];
            }
        }

        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < n-8+1; i++) {
            for (int j = 0; j < m-8+1; j++) {
                counts.add(getCount(board, i, j, "B"));
                counts.add(getCount(board, i, j, "W"));
            }
        }

        System.out.println(counts.stream().min(Integer::compare).get());

        in.close();
    }
}
