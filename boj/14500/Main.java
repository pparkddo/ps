import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point {

    int row;
    int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Shape {

    Point[] points;

    Shape(Point... points) {
        this.points = points;
    }
}

public class Main {

    private static final List<Shape> shapes = new ArrayList<Shape>(Arrays.asList(new Shape[] {
        // Line Shapes Turns
        new Shape(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)),
        new Shape(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)),

        // Square Shapes Turns
        new Shape(new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)),

        // L Shapes Turns
        new Shape(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1)),
        new Shape(new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, -2)),
        new Shape(new Point(0, 0), new Point(-1, 0), new Point(-2, 0), new Point(-2, -1)),
        new Shape(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(-1, 2)),

        // Thunder Shapes Turns
        new Shape(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)),
        new Shape(new Point(0, 0), new Point(0, -1), new Point(-1, -1), new Point(-1, -2)),
        new Shape(new Point(0, 0), new Point(-1, 0), new Point(-1, 1), new Point(-2, 1)),
        new Shape(new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)),

        // F* Shapes Turns
        new Shape(new Point(0, 0), new Point(0, 1), new Point(-1, 1), new Point(0, 2)),
        new Shape(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1)),
        new Shape(new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, -1)),
        new Shape(new Point(0, 0), new Point(-1, 0), new Point(-2, 0), new Point(-1, -1)),
    }));

    private static Shape getXSymmetry(Shape shape) {
        Point[] points = new Point[shape.points.length];
        for (int i = 0; i < shape.points.length; i++) {
            Point point = shape.points[i];
            points[i] = new Point(-point.row, point.column);
        }
        return new Shape(points);
    }
    
    private static Shape getYSymmetry(Shape shape) {
        Point[] points = new Point[shape.points.length];
        for (int i = 0; i < shape.points.length; i++) {
            Point point = shape.points[i];
            points[i] = new Point(point.row, -point.column);
        }
        return new Shape(points);
    }

    private static boolean isPossibleShape(int[][] board, int row, int column, Shape shape) {
        for (Point point : shape.points) {
            if (!isPossiblePoint(board, row, column, point)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPossiblePoint(int[][] board, int row, int column, Point point) {
        int computedRow = getComputedRow(row, point);
        int computedColumn = getComputedColumn(column, point);
        return (
            computedRow >= 0
            && computedRow < board.length
            && computedColumn >= 0
            && computedColumn < board[0].length
        );
    }

    private static int getComputedRow(int row, Point point) {
        return row + point.row;
    }

    private static int getComputedColumn(int column, Point point) {
        return column + point.column;
    }

    private static int getSum(int[][] board, int row, int column, Shape shape) {
        int sum = 0;
        for (Point point : shape.points) {
            sum += board[getComputedRow(row, point)][getComputedColumn(column, point)];
        }
        return sum;
    }

    private static void initialize() {
        List<Shape> symmetries = new ArrayList<>();
        for (Shape shape : shapes) {
            symmetries.add(getXSymmetry(shape));
            symmetries.add(getYSymmetry(shape));
        }
        shapes.addAll(symmetries);
    }

    public static void main(String[] args) throws IOException {
        initialize();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();

        int answer = Integer.MIN_VALUE;
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                for (Shape shape : shapes) {
                    if (!isPossibleShape(board, row, column, shape)) {
                        continue;
                    }
                    answer = Math.max(answer, getSum(board, row, column, shape));
                }
            }
        }

        System.out.println(answer);
    }
}
