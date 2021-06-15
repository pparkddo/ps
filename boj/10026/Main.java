import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static final int NO_GROUP_ID = 0;

    private static String solution(int n, char[][] picture) {
        return String.format("%d %d", getGroupCount(n, picture), getBlindnessGroupCount(n, picture));
    }

    private static int getGroupCount(int n, char[][] picture) {
        int[][] group = new int[n][n];
        int groupId = 1;
        for (int row = 0; row < picture.length; row++) {
            for (int column = 0; column < picture[0].length; column++) {
                if (group[row][column] != NO_GROUP_ID) {
                    continue;
                }
                grouping(row, column, group, picture, groupId++);
            }
        }
        return groupId - 1;
    }

    private static int getBlindnessGroupCount(int n, char[][] picture) {
        int[][] group = new int[n][n];
        int groupId = 1;
        for (int row = 0; row < picture.length; row++) {
            for (int column = 0; column < picture[0].length; column++) {
                if (group[row][column] != NO_GROUP_ID) {
                    continue;
                }
                groupingBlindness(row, column, group, picture, groupId++);
            }
        }
        return groupId - 1;
    }

    private static void groupingBlindness(int row, int column, int[][] group, char[][] picture, int groupId) {
        if (group[row][column] != NO_GROUP_ID) {
            return;
        }
        group[row][column] = groupId;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn, picture)) {
                continue;
            }
            if (!isSameBlindnessGroup(picture[row][column], picture[nextRow][nextColumn])) {
                continue;
            }
            groupingBlindness(nextRow, nextColumn, group, picture, groupId);
        }
    }

    private static boolean isSameBlindnessGroup(char value, char test) {
        if ((value == 'R' || value == 'G') && (test == 'R' || test == 'G')) {
            return true;
        }
        return value == test;
    }

    private static void grouping(int row, int column, int[][] group, char[][] picture, int groupId) {
        if (group[row][column] != NO_GROUP_ID) {
            return;
        }
        group[row][column] = groupId;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn, picture)) {
                continue;
            }
            if (!isSameGroup(picture[row][column], picture[nextRow][nextColumn])) {
                continue;
            }
            grouping(nextRow, nextColumn, group, picture, groupId);
        }
    }

    private static boolean isSameGroup(char value, char test) {
        return value == test;
    }

    private static boolean isValid(int row, int column, char[][] picture) {
        return row >= 0 && row < picture.length && column >= 0 && column < picture[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        char[][] picture = new char[n][n];
        for (int i = 0; i < n; i++) {
            String each = in.readLine();
            for (int j = 0; j < n; j++) {
                picture[i][j] = each.charAt(j);
            }
        }
        in.close();
        System.out.println(solution(n, picture));
    }
}
