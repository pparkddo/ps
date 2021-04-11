import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static Queue<MeltCount> meltCounts = new LinkedList<>();
    private static final int[] dr = {0, 0, 1, -1};
    private static final int[] dc = {1, -1, 0, 0};
    private static final int SEA = 0;

    private static boolean isAvailable(int[][] map, int row, int column) {
        return (
            row >= 0
            && row < map.length
            && column >= 0
            && column < map[0].length
        );
    }

    private static int getChunkCount(int[][] map) {
        int count = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (visited[row][column] || map[row][column] == SEA) {
                    continue;
                }
                updateChunkInformation(map, visited, row, column);
                count++;
            }
        }
        return count;
    }

    private static void updateChunkInformation(int[][] map, boolean[][] visited, int row, int column) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(map, nextRow, nextColumn) || map[nextRow][nextColumn] == SEA) {
                continue;
            }
            updateChunkInformation(map, visited, nextRow, nextColumn);
        }
    }

    private static MeltCount getMeltCount(int[][] map, int row, int column) {
        int count = 0;
        for (int direction = 0; direction < dr.length; direction++) {
            int adjacentRow = row + dr[direction];
            int adjacentColumn = column + dc[direction];
            if (!isAvailable(map, adjacentRow, adjacentColumn)) {
                continue;
            }
            count += map[adjacentRow][adjacentColumn] == SEA ? 1 : 0;
        }
        return new MeltCount(row, column, count);
    }

    private static void updateMeltCounts(int[][] map, boolean[][] visited, int row, int column) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        meltCounts.add(getMeltCount(map, row, column));
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(map, nextRow, nextColumn) || map[nextRow][nextColumn] == SEA) {
                continue;
            }
            updateMeltCounts(map, visited, nextRow, nextColumn);
        }
    }

    private static void melt(int[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                updateMeltCounts(map, visited, row, column);
            }
        }
        while (!meltCounts.isEmpty()) {
            MeltCount each = meltCounts.poll();
            map[each.row][each.column] = Math.max(map[each.row][each.column]-each.count, 0);
        }
    }

    private static boolean isEntireIceMelted(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != SEA) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int solution(int n, int m, int[][] map) {
        int year = 0;
        while (true) {
            if (getChunkCount(map) >= 2) {
                return year;
            }
            if (isEntireIceMelted(map)) {
                break;
            }
            melt(map);
            year++;
        }
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        System.out.println(solution(n, m, map));
    }
}

class MeltCount {

    int row;
    int column;
    int count;

    public MeltCount(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }
}
