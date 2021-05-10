import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final char BOMB = 'O';
    private static final char BLANK = '.';
    private static final int[] dr = {1, -1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static boolean isAvailable(int row, int column, int r, int c) {
        return (
            row >= 0
            && row < r
            && column >= 0
            && column < c
        );
    }

    private static void explode(char[][] map, int[][] times, int t) {
        int r = map.length;
        int c = map[0].length;
        List<Point> deleted = new ArrayList<>();
        for (int row = 0; row < r; row++) {
            for (int column = 0; column < c; column++) {
                if (map[row][column] == BOMB && times[row][column] == t - 3) {
                    deleted.add(new Point(row, column));
                    for (int direction = 0; direction < dr.length; direction++) {
                        deleted.add(new Point(row+dr[direction], column+dc[direction]));
                    }
                }
            }
        }
        for (Point point : deleted) {
            if (!isAvailable(point.row, point.column, r, c)) {
                continue;
            }
            map[point.row][point.column] = BLANK;
        }
    }

    private static void installBomb(char[][] map, int[][] times, int t) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == BLANK) {
                    map[row][column] = BOMB;
                    times[row][column] = t;
                }
            }
        }
    }

    private static String showMap(char[][] map) {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                s.append(map[row][column]);
            }
            s.append("\n");
        }
        return s.toString().trim();
    }

    private static String solution(int r, int c, int n, char[][] map) {
        int[][] times = new int[r][c];
        for (int t = 1; t <= n; t++) {
            explode(map, times, t);
            if (t % 2 == 1) {
                continue;
            }
            installBomb(map, times, t);
        }
        return showMap(map);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rcn = in.readLine().split(" ");
        int r = Integer.parseInt(rcn[0]);
        int c = Integer.parseInt(rcn[1]);
        int n = Integer.parseInt(rcn[2]);
        char[][] map = new char[r][c];
        for (int row = 0; row < r; row++) {
            String[] each = in.readLine().split("");
            for (int column = 0; column < c; column++) {
                map[row][column] = each[column].charAt(0);
            }
        }
        in.close();
        System.out.println(solution(r, c, n, map));
    }
}

class Point {
    
    int row;
    int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}