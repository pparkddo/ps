import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int BLANK = 0;
    private static final int WALL = 6;
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int OBSERVED = 7;

    private static List<Camera> getCameras(int[][] office) {
        List<Camera> cameras = new ArrayList<>();
        for (int i = 0; i < office.length; i++) {
            for (int j = 0; j < office[0].length; j++) {
                if (office[i][j] != BLANK && office[i][j] != WALL) {
                    cameras.add(new Camera(i, j, office[i][j]));
                }
            }
        }
        return cameras;
    }

    private static int getCounterClockWise(int direction) {
        return (4 + (direction - 1)) % 4;
    }

    private static int[] getDirections(int cameraType, int direction) {
        int counterClockWise = getCounterClockWise(direction);
        int opposite = getCounterClockWise(counterClockWise);
        switch (cameraType) {
            case 1:
                return new int[] {direction};
            case 2:
                return new int[] {direction, opposite};
            case 3:
                return new int[] {direction, counterClockWise};
            case 4:
                return new int[] {direction, counterClockWise, opposite};
            case 5:
                return new int[] {RIGHT, DOWN, LEFT, UP};
            default:
                throw new IllegalArgumentException("Unknown cameraType : " + cameraType);
        }
    }

    private static void observe(int[][] office, int row, int column, int direction) {
        switch (direction) {
            case RIGHT:
                observeRight(office, row, column);
                return;
            case DOWN:
                observeDown(office, row, column);
                return;
            case LEFT:
                observeLeft(office, row, column);
                return;
            case UP: 
                observeUp(office, row, column);
                return;
            default:
                throw new IllegalArgumentException("Unknown direction : " + direction);
        }
    }

    private static void observeRight(int[][] office, int row, int column) {
        for (int index = column+1; index < office[0].length; index++) {
            if (office[row][index] == WALL) {
                return;
            }
            if (office[row][index] == BLANK) {
                office[row][index] = OBSERVED;
            }
        }
    }

    private static void observeDown(int[][] office, int row, int column) {
        for (int index = row+1; index < office.length; index++) {
            if (office[index][column] == WALL) {
                return;
            }
            if (office[index][column] == BLANK) {
                office[index][column] = OBSERVED;
            }
        }
    }

    private static void observeLeft(int[][] office, int row, int column) {
        for (int index = column-1; index >= 0; index--) {
            if (office[row][index] == WALL) {
                return;
            }
            if (office[row][index] == BLANK) {
                office[row][index] = OBSERVED;
            }
        }
    }

    private static void observeUp(int[][] office, int row, int column) {
        for (int index = row-1; index >= 0; index--) {
            if (office[index][column] == WALL) {
                return;
            }
            if (office[index][column] == BLANK) {
                office[index][column] = OBSERVED;
            }
        }
    }

    private static int reset(int[][] office) {
        int blindSpotCount = 0;
        for (int i = 0; i < office.length; i++) {
            for (int j = 0; j < office[0].length; j++) {
                if (office[i][j] == BLANK) {
                    blindSpotCount++;
                }
                if (office[i][j] == OBSERVED) {
                    office[i][j] = BLANK;
                }
            }
        }
        return blindSpotCount;
    }

    private static int dfs(int depth ,int[][] office, List<Camera> cameras) {
        if (depth == cameras.size()) {
            for (Camera camera : cameras) {
                int[] directions = getDirections(camera.type, camera.direction);
                for (int direction : directions) {
                    observe(office, camera.row, camera.column, direction);
                }
            }
            return reset(office);
        }
        int min = Integer.MAX_VALUE;
        for (int rotation = 0; rotation < 4; rotation++) {
            cameras.get(depth).direction = rotation;
            min = Math.min(min, dfs(depth+1, office, cameras));
        }
        return min;
    }

    private static int solution(int n, int m, int[][] office) {
        List<Camera> cameras = getCameras(office);
        return dfs(0, office, cameras);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] office = new int[n][m];
        for (int i = 0; i < office.length; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < office[0].length; j++) {
                office[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, m, office));
    }
}

class Camera {

    int row;
    int column;
    int type;
    int direction;

    public Camera(int row, int column, int type) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.direction = 0;
    }

    @Override
    public String toString() {
        return row + " " + column + " " + type + " " + direction;
    }
}
