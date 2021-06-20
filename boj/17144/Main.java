import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int CLEANSER = -1;
    private static final int BLANK = 0;
    private static final int[] UP = {-1, 0};
    private static final int[] DOWN = {1, 0};
    private static final int[] LEFT = {0, -1};
    private static final int[] RIGHT = {0, 1};
    private static final int[][] DIRECTIONS = {UP, DOWN, LEFT, RIGHT};
    private static final int[] STAY = {0, 0};

    private static int solution(int r, int c, int t, int[][] room) {
        List<Integer> rows = getCleanserRows(room);

        int[][] amounts = new int[room.length][room[0].length];
        amounts[rows.get(0)][0] = CLEANSER;
        amounts[rows.get(1)][0] = CLEANSER;

        int[][][] circulations = new int[r][c][2];
        for (int row = 0; row < room.length; row++) {
            for (int column = 0; column < room[0].length; column++) {
                circulations[row][column] = getCirculationDirection(row, column, rows, r, c);
            }
        }

        while (t-- > 0) {
            spread(room, amounts);
            cycle(room, circulations, amounts);
        }

        return getTotalAmount(room);
    }

    private static int getTotalAmount(int[][] room) {
        int total = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j] == CLEANSER) {
                    continue;
                }
                total += room[i][j];
            }
        }
        return total;
    }

    private static void cycle(int[][] room, int[][][] circulations, int[][] amounts) {
        for (int row = 0; row < room.length; row++) {
            for (int column = 0; column < room[0].length; column++) {
                int[] direction = circulations[row][column];
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if (room[nextRow][nextColumn] == CLEANSER) {
                    continue;
                }
                amounts[nextRow][nextColumn] = room[row][column];
            }
        }
        for (int row = 0; row < amounts.length; row++) {
            for (int column = 0; column < amounts[0].length; column++) {
                if (room[row][column] == CLEANSER) {
                    continue;
                }
                room[row][column] = amounts[row][column];
                amounts[row][column] = 0;
            }
        }
    }

    private static int[] getCirculationDirection(int row, int column, List<Integer> rows, int r, int c) {
        if (row == rows.get(0) && column != 0 && column != c-1) {
            return RIGHT;
        }
        if (row != 0 && row <= rows.get(0) && column == c-1) {
            return UP;
        }
        if (row == 0 && column != 0) {
            return LEFT;
        }
        if (row < rows.get(0) && column == 0) {
            return DOWN;
        }
        if (row == rows.get(1) && column != 0 && column != c-1) {
            return RIGHT;
        }
        if (row != r-1 && row >= rows.get(1) && column == c-1) {
            return DOWN;
        }
        if (row == r-1 && column != 0) {
            return LEFT;
        }
        if (row > rows.get(1) && column == 0) {
            return UP;
        }
        return STAY;
    }

    private static List<Integer> getCleanserRows(int[][] room) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < room.length; row++) {
            if (room[row][0] == CLEANSER) {
                rows.add(row);
            }
        }
        return rows;
    }

    private static void spread(int[][] room, int[][] amounts) {
        for (int row = 0; row < amounts.length; row++) {
            for (int column = 0; column < amounts[0].length; column++) {
                if (room[row][column] > BLANK) {
                    int spreadAmount = 0;
                    int nextAmount = room[row][column] / 5;
                    for (int d = 0; d < DIRECTIONS.length; d++) {
                        int nextRow = row + DIRECTIONS[d][0];
                        int nextColumn = column + DIRECTIONS[d][1];
                        if (!isValid(nextRow, nextColumn, room)) {
                            continue;
                        }
                        if (room[nextRow][nextColumn] == CLEANSER) {
                            continue;
                        }
                        amounts[nextRow][nextColumn] += nextAmount;
                        spreadAmount += nextAmount;
                    }
                    amounts[row][column] -= spreadAmount;
                }
            }
        }

        for (int i = 0; i < amounts.length; i++) {
            for (int j = 0; j < amounts[0].length; j++) {
                if (room[i][j] == CLEANSER) {
                    continue;
                }
                room[i][j] += amounts[i][j];
                amounts[i][j] = 0;
            }
        }
    }

    private static boolean isValid(int row, int column, int[][] room) {
        return row >= 0 && row < room.length && column >= 0 && column < room[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rct = in.readLine().split(" ");
        int r = Integer.parseInt(rct[0]);
        int c = Integer.parseInt(rct[1]);
        int t = Integer.parseInt(rct[2]);
        int[][] room = new int[r][c];
        for (int i = 0; i < r; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(r, c, t, room));
    }
}
