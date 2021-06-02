import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final char SOURCE = 'S';
    private static final char DESTINATION = 'D';
    private static final char ROCK = 'X';
    private static final char WATER = '*';
    private static final char BLANK = '.';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static boolean isValid(int row, int column, char[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }

    private static void sink(int initialRow, int initialColumn, char[][] map, boolean[][] visited) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(initialRow, initialColumn));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            final int row = position.row;
            final int column = position.column;
            if (visited[row][column]) {
                continue;
            }
            visited[row][column] = true;
            if (map[row][column] == BLANK || map[row][column] == SOURCE) {
                map[row][column] = WATER;
                continue;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (map[nextRow][nextColumn] == DESTINATION) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (map[nextRow][nextColumn] == ROCK) {
                    continue;
                }
                queue.add(new Position(nextRow, nextColumn));
            }
        }
    }

    private static List<Position> move(int row, int column, char[][] map, boolean[][] visited) {
        List<Position> positions = new ArrayList<>();
        visited[row][column] = true;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn, map)) {
                continue;
            }
            if (visited[nextRow][nextColumn]) {
                continue;
            }
            if (map[nextRow][nextColumn] == WATER) {
                continue;
            }
            if (map[nextRow][nextColumn] == ROCK) {
                continue;
            }
            positions.add(new Position(nextRow, nextColumn));
        }
        return positions;
    }

    private static String solution(int r, int c, char[][] map) {
        boolean[][] visited = new boolean[r][c];
        String answer = null;
        int time = 0;

        while (true) {
            boolean hasAnySource = false;

            List<Position> movements = new ArrayList<>();
            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map[0].length; column++) {
                    if (map[row][column] != SOURCE) {
                        continue;
                    }
                    hasAnySource = true;
                    movements.addAll(move(row, column, map, visited));
                }
            }
            
            for (Position each : movements) {
                final int row = each.row;
                final int column = each.column;
                if (map[row][column] == DESTINATION) {
                    return String.valueOf(time+1);
                }
                map[row][column] = SOURCE;
            }

            boolean[][] waterVisited = new boolean[r][c];
            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map[0].length; column++) {
                    if (map[row][column] != WATER) {
                        continue;
                    }
                    if (waterVisited[row][column]) {
                        continue;
                    }
                    sink(row, column, map, waterVisited);
                }
            }

            time++;
            if (!hasAnySource) {
                answer = "KAKTUS";
                break;
            }
        }

        return answer;
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

class Position {

    int row;
    int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return row + " " + column;
    }
}