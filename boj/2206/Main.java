import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {

    public int row;
    public int column;
    public boolean canBreak;
    public int depth;

    Node(int row, int column, boolean canBreak, int depth) {
        this.row = row;
        this.column = column;
        this.canBreak = canBreak;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return (
            this.row == node.row
            && this.column == node.column
            && this.canBreak == node.canBreak
        );
    }

    @Override
    public int hashCode() {
        int c = 31;

        int result = Integer.hashCode(this.row);
        result = c * result + Integer.hashCode(this.column);
        result = c * result + Boolean.hashCode(this.canBreak);

        return result;
    }
}

public class Main {

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean isPossible(int row, int column, int n, int m) {
        if (row < 0 || row >= n) {
            return false;
        }
        if (column < 0 || column >= m) {
            return false;
        }
        return true;
    }

    private static boolean isEnd(int row, int column, int n, int m) {
        return row == n-1 && column == m-1;
    }

    private static int getLeastDistance(int[][] map) {
        Queue<Node> queue = new LinkedList<>();
        List<Integer> candidates = new ArrayList<>();
        queue.add(new Node(0, 0, true, 1));
        int n = map.length;
        int m = map[0].length;
        boolean[][][] visited = new boolean[2][n][m];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int depth = node.depth;
            boolean canBreak = node.canBreak;
            if (visited[canBreak ? 1 : 0][row][column]) {
                continue;
            }
            visited[canBreak ? 1 : 0][row][column] = true;
            if (isEnd(row, column, n, m)) {
                candidates.add(depth);
                continue;
            }

            for (int i = 0; i < dx.length; i++) {
                int nextColumn = column + dx[i];
                int nextRow = row + dy[i];
                
                if (!isPossible(nextRow, nextColumn, n, m)) {
                    continue;
                }

                if (map[nextRow][nextColumn] == 1 && canBreak) {
                    queue.add(new Node(nextRow, nextColumn, false, depth+1));
                }
                if (map[nextRow][nextColumn] == 1) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, canBreak, depth+1));
            }
        }
        return !candidates.isEmpty() ? candidates.stream().min(Integer::compare).get() : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        int answer = getLeastDistance(map);
        System.out.println(answer);
    }
}
