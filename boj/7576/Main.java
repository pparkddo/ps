import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    int row;
    int column;
    int depth;

    Node(int row, int column, int depth) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }
}

public class Main {

    private static int maxDepth = 0;
    private static int count = 0;
    private static int[][] box;
    private static Queue<Node> queue = new LinkedList<>();

    public static void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int depth = node.depth;
            maxDepth = Math.max(maxDepth, depth);
            count = count + 1;

            if (row > 0 && box[row-1][column] == 0) {
                box[row-1][column] = 1;
                queue.add(new Node(row-1, column, depth+1));
            }

            if (column < box[0].length-1 && box[row][column+1] == 0) {
                box[row][column+1] = 1;
                queue.add(new Node(row, column+1, depth+1));
            }

            if (row < box.length-1 && box[row+1][column] == 0) {
                box[row+1][column] = 1;
                queue.add(new Node(row+1, column, depth+1));
            }

            if (column > 0 && box[row][column-1] == 0) {
                box[row][column-1] = 1;
                queue.add(new Node(row, column-1, depth+1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = in.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        box = new int[n][m];
        int target = 0;
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int tomato = Integer.parseInt(each[j]);
                box[i][j] = tomato;
                if (tomato == 0 || tomato == 1) {
                    target = target + 1;
                }
                if (tomato == 1) {
                    queue.add(new Node(i, j, 0));
                }
            }
        }
        in.close();

        bfs();

        if (count != target) {
            System.out.println(-1);
            return;
        }
        System.out.println(maxDepth);
    }
}
