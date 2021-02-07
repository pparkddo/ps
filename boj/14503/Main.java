import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {

    private int row;
    private int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

class Robot {
    
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, 1, 0, -1};
    private int initialRow;
    private int initialColumn;
    private int direction;
    private int[][] area;
    private boolean[][] cleaned;
    private int count = 0;

    Robot(int row, int column, int direction, int[][] area) {
        this.initialRow = row;
        this.initialColumn = column;
        this.direction = direction;
        this.area = area;
        this.cleaned = new boolean[area.length][area[0].length];
    }

    public void clean() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(initialRow, initialColumn));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.getRow();
            int column = point.getColumn();
            if (!this.cleaned[row][column]) {
                this.cleaned[row][column] = true;
                count += 1;
            }
            boolean shouldGoBack = true;
            for (int i = 0; i < 4; i++) {
                this.rotate();
                int nextRow = row + this.dx[this.direction];
                int nextColumn = column + this.dy[this.direction];
                if (!isPossible(nextRow, nextColumn)) {
                    continue;
                }
                if (this.area[nextRow][nextColumn] == 1) {
                    continue;
                }
                if (this.cleaned[nextRow][nextColumn]) {
                    continue;
                }
                shouldGoBack = false;
                queue.add(new Point(nextRow, nextColumn));
                break;
            }
            if (!shouldGoBack) {
                continue;
            }
            int backDirection = getBackDirection();
            int nextRow = row + this.dx[backDirection];
            int nextColumn = column + this.dy[backDirection];
            if (this.area[nextRow][nextColumn] == 1) {
                return;
            }
            queue.add(new Point(nextRow, nextColumn));
        }
    }

    public int getCount() {
        return this.count;
    }

    private void rotate() {
        if (this.direction == 0) {
            this.direction = 3;
            return;
        }
        this.direction -= 1;
    }

    private boolean isPossible(int row, int column) {
        return (
            row > 0
            && row < this.area.length-1
            && column > 0
            && column < this.area[0].length-1
        );
    }

    private int getBackDirection() {
        switch (this.direction) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
            default:
                return 1;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] rcd = in.readLine().split(" ");
        int r = Integer.parseInt(rcd[0]);
        int c = Integer.parseInt(rcd[1]);
        int d = Integer.parseInt(rcd[2]);

        int[][] area = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                area[i][j] = Integer.parseInt(input[j]);
            }
        }
        in.close();

        Robot robot = new Robot(r, c, d, area);
        robot.clean();
        System.out.println(robot.getCount());
    }
}
