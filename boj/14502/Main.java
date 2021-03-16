import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Point {

    int row;
    int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

public class Main {

    private static final int SAFE_AREA = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static void initialize(int[][] initialMap, int[][] map, boolean[][] visited) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = initialMap[i][j];
                visited[i][j] = false;
            }
        }
    }

    private static int getRow(int index, int columnLength) {
        return index / columnLength;
    }

    private static int getColumn(int index, int columnLength) {
        return index % columnLength;
    }

    private static boolean canBuildWall(int[][] map, int row, int column) {
        return map[row][column] == 0;
    }

    private static boolean canBuildWalls(int[][] map, List<Point> walls) {
        boolean canBuild = true;
        for (Point wall : walls) {
            if (!canBuildWall(map, wall.row, wall.column)) {
                canBuild = false;
            }
        }
        return canBuild;
    }

    private static void buildWalls(int[][] map, List<Point> walls) {
        for (Point wall : walls) {
            map[wall.row][wall.column] = WALL;
        }
    }

    private static boolean canSpread(int[][] map, int row, int column) {
        if (
            row < 0
            || row >= map.length
            || column < 0
            || column >= map[0].length
        ) {
            return false;
        }
        return map[row][column] != WALL;
    }

    private static void spread(int[][] map, boolean[][] visited, int row, int column) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (canSpread(map, nextRow, nextColumn)) {
                spread(map, visited, nextRow, nextColumn);
                map[row][column] = VIRUS;
            }
        }
    }

    private static int getSafeAreaSize(int[][] map) {
        int size = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == SAFE_AREA) {
                    size++;
                }
            }
        }
        return size;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] initialMap = new int[n][m];
        List<Point> virusPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(each[j]);
                initialMap[i][j] = value;
                if (value == VIRUS) {
                    virusPoints.add(new Point(i, j));
                }
            }
        }
        in.close();

        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int totalIndex = n * m;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < totalIndex-2; i++) {
            for (int j = i+1; j < totalIndex-1; j++) {
                for (int k = j+1; k < totalIndex; k++) {
                    List<Point> walls = new ArrayList<>();
                    walls.add(new Point(getRow(i, m), getColumn(i, m)));
                    walls.add(new Point(getRow(j, m), getColumn(j, m)));
                    walls.add(new Point(getRow(k, m), getColumn(k, m)));
                    if (!canBuildWalls(initialMap, walls)) {
                        continue;
                    }
                    initialize(initialMap, map, visited);
                    buildWalls(map, walls);
                    for (Point point : virusPoints) {
                        spread(map, visited, point.row, point.column);
                    }
                    answer = Math.max(answer, getSafeAreaSize(map));
                }
            }
        }
        System.out.println(answer);
    }
}
