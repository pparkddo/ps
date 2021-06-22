import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int[][] MOVEMENTS = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int DRAGON_CURVE = 1;
    private static final int MAX_BOARD_SIZE = 101;

    private static void draw(int x, int y, int d, int g, int[][] board) {
        board[y][x] = DRAGON_CURVE;

        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        draw(directions, g, 0, board);

        for (Integer direction : directions) {
            int[] movement = getMovement(direction);
            x += movement[1];
            y += movement[0];
            board[y][x] = DRAGON_CURVE;
        }
    }

    private static void draw(List<Integer> directions, int g, int generation, int[][] board) {
        if (generation == g) {
            return;
        }
        List<Integer> nextDirections = new ArrayList<>();
        for (int index = directions.size()-1; index >= 0; index--) {
            int rotated = rotate(directions.get(index));
            nextDirections.add(rotated);
        }
        directions.addAll(nextDirections);
        draw(directions, g, generation+1, board);
    }

    private static int[] getMovement(int direction) {
        return MOVEMENTS[direction];
    }

    private static int rotate(int direction) {
        return (direction + 1) % 4;
    }

    private static int getDragonCurveSquareCount(int[][] board) {
        int count = 0;
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[0].length - 1; j++) {
                if (board[i][j] == DRAGON_CURVE
                    && board[i+1][j] == DRAGON_CURVE
                    && board[i][j+1] == DRAGON_CURVE
                    && board[i+1][j+1] == DRAGON_CURVE) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String[] xydg = in.readLine().split(" ");
            int x = Integer.parseInt(xydg[0]);
            int y = Integer.parseInt(xydg[1]);
            int d = Integer.parseInt(xydg[2]);
            int g = Integer.parseInt(xydg[3]);
            draw(x, y, d, g, board);
        }
        in.close();
        System.out.println(getDragonCurveSquareCount(board));
    }
}
