import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static final int[] dhr = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] dhc = {-2, -1, 1, 2, -2, -1, 1, 2};

    private static final int FIELD = 0;

    private static boolean isAvailable(int row, int column, int[][] board) {
        return (
            row >= 0
            && row < board.length 
            && column >= 0
            && column < board[0].length
            && board[row][column] == FIELD
        );
    }

    private static boolean isFinished(int row, int column, int h, int w) {
        return row == h-1 && column == w-1;
    }

    private static int solution(int k, int w, int h, int[][] board) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, k));
        boolean[][][] visited = new boolean[k+1][h][w];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited[node.k][node.row][node.column]) {
                continue;
            }
            visited[node.k][node.row][node.column] = true;
            if (isFinished(node.row, node.column, h, w)) {
                return node.count;
            }
            if (node.k > 0) {
                for (int index = 0; index < dhr.length; index++) {
                    int nextRow = node.row + dhr[index];
                    int nextColumn = node.column + dhc[index];
                    if (!isAvailable(nextRow, nextColumn, board)) {
                        continue;
                    }
                    queue.add(new Node(nextRow, nextColumn, node.count+1, node.k-1));
                }
            }
            for (int index = 0; index < dr.length; index++) {
                int nextRow = node.row + dr[index];
                int nextColumn = node.column + dc[index];
                if (!isAvailable(nextRow, nextColumn, board)) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, node.count+1, node.k));
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine());
        String[] wh = in.readLine().split(" ");
        int w = Integer.parseInt(wh[0]);
        int h = Integer.parseInt(wh[1]);
        int[][] board = new int[h][w];
        for (int i = 0; i < h; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(k, w, h, board));
    }
}

class Node {

    int row;
    int column;
    int count;
    int k;

    Node(int row, int column, int count, int k) {
        this.row = row;
        this.column = column;
        this.count = count;
        this.k = k;
    }
}
