import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final char SWAN = 'L';
    private static final char ICE = 'X';
    private static final char WATER = '.';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int r, int c, char[][] lake, List<Swan> swans, Queue<Water> waters) {
        Queue<Movement> movements = new LinkedList<>();
        Swan source = swans.get(0);
        Swan destination = swans.get(1);
        movements.add(new Movement(source.row, source.column));

        boolean[][] visited = new boolean[r][c];
        boolean[][] waterVisited = new boolean[r][c];

        melt(waters, waterVisited, lake);

        int answer = 0;
        while (!move(destination, movements, visited, lake)) {
            melt(waters, waterVisited, lake);
            answer++;
        }
        return answer;
    }

    private static boolean move(Swan destination, Queue<Movement> movements, boolean[][] visited, char[][] lake) {
        Queue<Movement> buffer = new LinkedList<>();
        while (!movements.isEmpty()) {
            Movement movement = movements.poll();
            if (movement.row == destination.row && movement.column == destination.column) {
                return true;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = movement.row + dr[d];
                int nextColumn = movement.column + dc[d];
                if (!isValid(nextRow, nextColumn, lake)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (lake[nextRow][nextColumn] == ICE) {
                    buffer.add(new Movement(nextRow, nextColumn));
                } else {
                    movements.add(new Movement(nextRow, nextColumn));
                }
                visited[nextRow][nextColumn] = true;
            }
        }
        movements.addAll(buffer);
        return false;
    }

    private static void melt(Queue<Water> waters, boolean[][] visited, char[][] lake) {
        int size = waters.size();
        for (int i = 0; i < size; i++) {
            Water water = waters.poll();
            lake[water.row][water.column] = WATER;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = water.row + dr[d];
                int nextColumn = water.column + dc[d];
                if (!isValid(nextRow, nextColumn, lake)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (lake[nextRow][nextColumn] == ICE) {
                    waters.add(new Water(nextRow, nextColumn));
                    visited[nextRow][nextColumn] = true;
                }
            }
        }
    }

    private static boolean isValid(int row, int column, char[][] lake) {
        return row >= 0 && row < lake.length && column >= 0 && column < lake[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        char[][] lake = new char[r][c];
        List<Swan> swans = new ArrayList<>();
        Queue<Water> ices = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String each = in.readLine();
            for (int j = 0; j < c; j++) {
                lake[i][j] = each.charAt(j);
                if (lake[i][j] == SWAN) {
                    swans.add(new Swan(i, j));
                    ices.add(new Water(i, j));
                }
                if (lake[i][j] == WATER) {
                    ices.add(new Water(i, j));
                }
            }
        }
        in.close();
        System.out.println(solution(r, c, lake, swans, ices));
    }
}

class Swan {

    int row;
    int column;

    Swan(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Water {

    int row;
    int column;

    Water(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Movement {

    int row;
    int column;

    Movement(int row, int column) {
        this.row = row;
        this.column = column;
    }
}