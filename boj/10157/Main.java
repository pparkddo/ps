import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String UNAVAILABLE = "0";
    private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static String solution(int c, int r, int k) {
        if (!isAvailable(c, r, k)) {
            return UNAVAILABLE;
        }
        boolean[][] seats = new boolean[r][c];
        int index = 0;
        int row = seats.length - 1;
        int column = 0;
        int directionIndex = 0;
        while (true) {
            if (index == k-1) {
                break;
            }
            int[] direction = directions[directionIndex];
            int nextRow = row + direction[0];
            int nextColumn = column + direction[1];
            if (!isValid(nextRow, nextColumn, seats) || seats[nextRow][nextColumn]) {
                directionIndex = nextDirectionIndex(directionIndex);
                continue;
            }
            seats[row][column] = true;
            row = nextRow;
            column = nextColumn;
            index++;
        }
        return getAnswer(row, column, r);
    }

    private static boolean isAvailable(int c, int r, int k) {
        return k <= c * r;
    }

    private static boolean isValid(int row, int column, boolean[][] seats) {
        return row >= 0 && row < seats.length && column >= 0 && column < seats[0].length;
    }

    private static int nextDirectionIndex(int directionIndex) {
        return (directionIndex+1) % 4;
    }

    private static String getAnswer(int row, int column, int r) {
        return (column+1) +  " " + (r-row);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] cr = in.readLine().split(" ");
        int c = Integer.parseInt(cr[0]);
        int r = Integer.parseInt(cr[1]);
        int k = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(c, r, k));
    }
}
