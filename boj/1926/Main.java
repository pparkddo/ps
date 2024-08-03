import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int PICTURE = 1;
    private static final int VISITED = -1;
    private static int N;
    private static int M;
    private static int NUMBER_OF_PICTURES = 0;
    private static int PICTURE_SIZE = 0;
    private static int LARGEST_PICTURE_SIZE = 0;
    private static int[][] BOARD;

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = in.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        BOARD = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] row = in.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                BOARD[i][j] = Integer.parseInt(row[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (BOARD[i][j] != PICTURE) {
                    continue;
                }
                NUMBER_OF_PICTURES++;
                PICTURE_SIZE = 1;
                dfs(i, j);
            }
        }

        System.out.println(NUMBER_OF_PICTURES);
        System.out.println(LARGEST_PICTURE_SIZE);
    }

    private static void dfs(int r, int c) {
        BOARD[r][c] = VISITED;
        LARGEST_PICTURE_SIZE = Math.max(LARGEST_PICTURE_SIZE, PICTURE_SIZE);

        for (int i = 0; i < 4; i++) {
            int newRow = r + DIRECTIONS[i][0];
            int newCol = c + DIRECTIONS[i][1];
            if (!isAvailable(newRow, newCol)) {
                continue;
            }
            if (BOARD[newRow][newCol] != PICTURE) {
                continue;
            }
            PICTURE_SIZE++;
            dfs(newRow, newCol);
        }
    }

    private static boolean isAvailable(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
