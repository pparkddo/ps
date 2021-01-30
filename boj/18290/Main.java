import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int max = Integer.MIN_VALUE;

    private static int getIndex(int m, int row, int column) {
        return row * m + column;
    }

    private static void assignAdjacent(int n, int m, int row, int column, int[][] isAdjacent, int value) {
        if (row > 0) {
            isAdjacent[row-1][column] += value;
        }
        if (row < n-1) {
            isAdjacent[row+1][column] += value;
        }
        if (column > 0) {
            isAdjacent[row][column-1] += value;
        }
        if (column < m-1) {
            isAdjacent[row][column+1] += value;
        }
    }

    private static void dfs(
        int[][] board,
        int n,
        int m,
        int k,
        int startIndex,
        int depth,
        int[] node,
        int[][] isAdjacent
    ) {
        if (depth == k) {
            int sum = Arrays.stream(node).sum();
            max = Math.max(sum, max);
            return;
        }
        
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                int index = getIndex(m, row, column);
                if (index < startIndex) {
                    continue;
                }
                if (isAdjacent[row][column] > 0) {
                    continue;
                }

                assignAdjacent(n, m, row, column, isAdjacent, 1);
                
                node[depth] = board[row][column];
                dfs(board, n, m, k, index+1, depth+1, node, isAdjacent);

                assignAdjacent(n, m, row, column, isAdjacent, -1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int column = 0; column < m; column++) {
                board[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();

        int depth = 0;
        int startIndex = 0;
        int[] node = new int[k];
        int[][] isAdjacent = new int[n][m];
        dfs(board, n, m, k, startIndex, depth, node, isAdjacent);
        System.out.println(max);
    }
}