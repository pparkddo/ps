import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int solution(int n, int l, int[][] map) {
        int count = 0;

        for (int row = 0; row < n; row++) {
            boolean[] ramp = new boolean[n];
            boolean canPass = true;
            for (int column = 1; column < n; column++) {
                int current = map[row][column];
                if (isFlat(map[row][column-1], current)) {
                    continue;
                }
                if (requireAscent(map[row][column-1], current)
                        && !hasRamp(row, column, l, ramp, true)
                        && canBuildAscentRamp(row, column, l, map, true)) {
                    for (int i = 1; i <= l; i++) {
                        ramp[column-i] = true;
                    }
                    continue;
                }
                if (requireDescent(map[row][column-1], current)
                        && canBuildDescentRamp(row, column, map[row][column-1], l, map, true)) {
                    for (int i = 0; i < l; i++) {
                        ramp[column+i] = true;
                    }
                    continue;
                }
                canPass = false;
            }
            if (canPass) {
                count++; 
            }
        }

        for (int column = 0; column < n; column++) {
            boolean[] ramp = new boolean[n];
            boolean canPass = true;
            for (int row = 1; row < n; row++) {
                int current = map[row][column];
                if (isFlat(map[row-1][column], current)) {
                    continue;
                }
                if (requireAscent(map[row-1][column], current)
                        && !hasRamp(row, column, l, ramp, false)
                        && canBuildAscentRamp(row, column, l, map, false)) {
                    for (int i = 1; i <= l; i++) {
                        ramp[row-i] = true;
                    }
                    continue;
                }
                if (requireDescent(map[row-1][column], current)
                        && canBuildDescentRamp(row, column, map[row-1][column], l, map, false)) {
                    for (int i = 0; i < l; i++) {
                        ramp[row+i] = true;
                    }
                    continue;
                }
                canPass = false;
            }
            if (canPass) {
                count++; 
            }
        }

        return count;
    }

    private static boolean hasRamp(int row, int column, int l, boolean[] ramp, boolean isColumnWise) {
        if (isColumnWise) {
            for (int i = 1; i <= l; i++) {
                if (!isValid(column-i, ramp)) {
                    return false;
                }
                if (ramp[column-i]) {
                    return true;
                }
            }
            return false;
        }

       for (int i = 1; i <= l; i++) {
           if (!isValid(row-i, ramp)) {
               return false;
           }
           if (ramp[row-i]) {
               return true;
           }
       }
       return false;
    }

    private static boolean canBuildAscentRamp(int row, int column, int l, int[][] map, boolean isColumnWise) {
        List<Integer> values = new ArrayList<>();

        if (isColumnWise) {
            for (int i = 1; i <= l; i++) {
                if (!isValid(row, column-i, map)) {
                    return false;
                }
                values.add(map[row][column-i]);
            }
        } else {
            for (int i = 1; i <= l; i++) {
                if (!isValid(row-i, column, map)) {
                    return false;
                }
                values.add(map[row-i][column]);
            }
        }

        for (Integer value : values) {
            if (value != map[row][column] - 1) {
                return false;
            }
            if (value != values.get(0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canBuildDescentRamp(int row, int column, int previous, int l, int[][] map, boolean isColumnWise) {
        List<Integer> values = new ArrayList<>();

        if (isColumnWise) {
            for (int i = 0; i < l; i++) {
                if (!isValid(row, column+i, map)) {
                    return false;
                }
                values.add(map[row][column+i]);
            }
        } else {
            for (int i = 0; i < l; i++) {
                if (!isValid(row+i, column, map)) {
                    return false;
                }
                values.add(map[row+i][column]);
            }
        }

        for (Integer value : values) {
            if (value != previous - 1) {
                return false;
            }
            if (value != values.get(0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFlat(int previous, int current) {
        return previous == current; 
    }

    private static boolean requireDescent(int previous, int current) {
        return previous > current;
    }

    private static boolean requireAscent(int previous, int current) {
        return previous < current;
    }

    private static boolean isValid(int index, boolean[] ramp) {
        return index >= 0 && index < ramp.length;
    }

    private static boolean isValid(int row, int column, int[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nl = in.readLine().split(" ");
        int n = Integer.parseInt(nl[0]);
        int l = Integer.parseInt(nl[1]);
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, l, map));
    }
}
