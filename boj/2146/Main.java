import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static final int SEA = 0;
    private static final int NOT_VISITED = -1;

    private static int solution(int n, int[][] map) {
        numbering(n, map);
        return getShortestPath(map);
    }

    private static int getShortestPath(int[][] map) {
        int[][] distances = new int[map.length][map[0].length];
        for (int row = 0; row < distances.length; row++) {
            Arrays.fill(distances[row], NOT_VISITED);
        }

        Queue<Node> queue = new LinkedList<>();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == SEA) {
                    continue;
                }
                queue.add(new Node(row, column, 0));
            }
        }

        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int count = node.count;
            distances[row][column] = count;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (distances[nextRow][nextColumn] != NOT_VISITED) {
                    continue;
                }
                int nextValue = map[nextRow][nextColumn];
                if (nextValue != SEA) {
                    continue;
                }
                int nextCount = count + 1;
                distances[nextRow][nextColumn] = nextCount;
                map[nextRow][nextColumn] = map[row][column];
                queue.add(new Node(nextRow, nextColumn, nextCount));
            }
        }

        int min = Integer.MAX_VALUE;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                for (int d = 0; d < dr.length; d++) {
                    int nextRow = row + dr[d];
                    int nextColumn = column + dc[d];
                    if (!isValid(nextRow, nextColumn, map)) {
                        continue;
                    }
                    if (map[row][column] == map[nextRow][nextColumn]) {
                        continue;
                    }
                    min = Math.min(min, distances[row][column] + distances[nextRow][nextColumn]);
                }
            }
        }

        return min;
    }

    private static void numbering(int n, int[][] map) {
        int id = 1;
        boolean[][] visited = new boolean[n][n];
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == 0) {
                    continue;
                }
                if (visited[row][column]) {
                    continue;
                }
                dfs(row, column, id++, map, visited);
            }
        }
    }

    private static void dfs(int row, int column, int id, int[][] map, boolean[][] visited) {
        if (visited[row][column]) {
            return;
        }
        map[row][column] = id;
        visited[row][column] = true;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn, map)) {
                continue;
            }
            if (map[nextRow][nextColumn] == SEA) {
                continue;
            }
            dfs(nextRow, nextColumn, id, map, visited);
        }
    }

    private static boolean isValid(int row, int column, int[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, map));
    }
}

class Node {

    int row;
    int column;
    int count;

    Node(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }

    @Override
    public String toString() {
        return row + " " + column + " " + count;
    }
}