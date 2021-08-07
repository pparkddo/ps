import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    private static final int FORBIDDEN = 1;
    private static final Map<Integer, List<Integer>> delta = Map.of(
        1, List.of(0, 1),
        2, List.of(0, -1),
        3, List.of(1, 0),
        4, List.of(-1, 0)
    );

    private static int solution(int m, int n, int[][] map, Position start, Position end) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][5];

        queue.add(new Node(start.row, start.column, start.direction, 0));
        visited[start.row][start.column][start.direction] = true;

        int minimum = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int direction = node.direction;
            int count = node.count;
            if (row == end.row && column == end.column && direction == end.direction) {
                minimum = Math.min(minimum, count);
                continue;
            }
            for (int g = 1; g <= 3; g++) {
                int nextRow = row + delta.get(direction).get(0) * g;
                int nextColumn = column + delta.get(direction).get(1) * g;
                if (!isValid(nextRow, nextColumn, map) || map[nextRow][nextColumn] == FORBIDDEN) {
                    break;
                }
                if (!visited[nextRow][nextColumn][direction]) {
                    queue.add(new Node(nextRow, nextColumn, direction, count+1));
                    visited[nextRow][nextColumn][direction] = true;
                }
            }
            for (int nextDirection = 1; nextDirection <= 4; nextDirection++) {
                int nextCount = isReverseDirection(direction, nextDirection) ? count+2 : count+1;
                if (!visited[row][column][nextDirection]) {
                    queue.add(new Node(row, column, nextDirection, nextCount));
                    visited[row][column][nextDirection] = true;
                }
            }
        }
        return minimum;
    }

    private static boolean isReverseDirection(int direction, int nextDirection) {
        if (direction == 1 && nextDirection == 2) {
            return true;
        }
        if (direction == 2 && nextDirection == 1) {
            return true;
        }
        if (direction == 3 && nextDirection == 4) {
            return true;
        }
        return direction == 4 && nextDirection == 3;
    }

    private static boolean isValid(int row, int column, int[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = in.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < each.length; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        Position start = getPosition(in.readLine().split(" "));
        Position end = getPosition(in.readLine().split(" "));
        in.close();
        System.out.println(solution(m, n, map, start, end));
    }

    private static Position getPosition(String[] values) {
        int row = Integer.parseInt(values[0]) - 1;
        int column = Integer.parseInt(values[1]) - 1;
        int direction = Integer.parseInt(values[2]);
        return new Position(row, column, direction);
    }
}

class Position {

    int row;
    int column;
    int direction;

    Position(int row, int column, int direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }
}

class Node {

    int row;
    int column;
    int direction;
    int count;

    Node(int row, int column, int direction, int count) {
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.count = count;
    }
}