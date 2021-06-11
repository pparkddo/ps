import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final char WATER = 'W';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int r, int c, char[][] map) {
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == WATER) {
                    continue;
                }
                answer = Math.max(answer, getLargestDistance(i, j, map));
            }
        }
        return answer;
    }

    private static int getLargestDistance(int initialRow, int initialColumn, char[][] map) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn, 0));

        boolean[][] visited = new boolean[map.length][map[0].length];

        int largestDistance = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int distance = node.distance;
            visited[row][column] = true;
            largestDistance = Math.max(largestDistance, distance);
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (map[nextRow][nextColumn] == WATER) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                visited[nextRow][nextColumn] = true;
                queue.add(new Node(nextRow, nextColumn, distance+1));
            }
        }

        return largestDistance;
    }

    private static boolean isValid(int row, int column, char[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String each = in.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = each.charAt(j);
            }
        }
        in.close();
        System.out.println(solution(r, c, map));
    }
}

class Node {

    int row;
    int column;
    int distance;

    Node(int row, int column, int distance) {
        this.row = row;
        this.column = column;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return row + " " + column + " " + distance;
    }
}