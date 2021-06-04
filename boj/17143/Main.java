import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int NOT_FOUND = -1;
    private static final int BLANK = 0;

    private static int solution(int r, int c, int m, List<Shark> sharks) {
        Map<Integer, Shark> sharkContainer = new HashMap<>();
        int[][] board = new int[r][c];
        for (int id = 1; id <= sharks.size(); id++) {
            int index = id - 1;
            Shark shark = sharks.get(index);
            board[shark.row][shark.column] = id;
            sharkContainer.put(id, shark);
        }

        int answer = 0;
        for (int column = 0; column < board[0].length; column++) {
            int id = NOT_FOUND;
            for (int row = 0; row < board.length; row++) {
                if (board[row][column] != BLANK) {
                    id = board[row][column];
                    break;
                }
            }
            for (Integer key : sharkContainer.keySet()) {
                Shark shark = sharkContainer.get(key);
                board[shark.row][shark.column] = BLANK;
                shark.move(r, c);
            }
            if (id != NOT_FOUND) {
                answer += sharkContainer.get(id).size;
                sharkContainer.remove(id);
            }
            List<Integer> removable = new ArrayList<>();
            for (Integer key : sharkContainer.keySet()) {
                Shark shark = sharkContainer.get(key);
                if (board[shark.row][shark.column] == BLANK) {
                    board[shark.row][shark.column] = key;
                    continue;
                }
                int existKey = board[shark.row][shark.column];
                if (sharkContainer.get(existKey).size > sharkContainer.get(key).size) {
                    removable.add(key);
                } else {
                    removable.add(existKey);
                    board[shark.row][shark.column] = key;
                }
            }
            for (Integer each : removable) {
                sharkContainer.remove(each);
            }
        }
        return answer;
    }

    private static int getOptimalSpeed(int direction, int speed, int r, int c) {
        if (direction == 1 || direction == 2) {
            return speed % (r*2-2); 
        } 
        else if (direction == 3 || direction == 4) {
            return speed % (c*2-2);
        }
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rcm = in.readLine().split(" ");
        int r = Integer.parseInt(rcm[0]);
        int c = Integer.parseInt(rcm[1]);
        int m = Integer.parseInt(rcm[2]);
        List<Shark> sharks = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] rcsdz = in.readLine().split(" ");
            int row = Integer.parseInt(rcsdz[0]) - 1;
            int column = Integer.parseInt(rcsdz[1]) - 1;
            int direction = Integer.parseInt(rcsdz[3]);
            int speed = getOptimalSpeed(direction, Integer.parseInt(rcsdz[2]), r, c);
            int size = Integer.parseInt(rcsdz[4]);
            sharks.add(new Shark(row, column, speed, direction, size));
        }
        in.close();
        System.out.println(solution(r, c, m, sharks));
    }
}

class Shark {

    int row;
    int column;
    int speed;
    int direction;
    int size;
    private static final int UP = 1;
    private static final int DOWN= 2;
    private static final int RIGHT= 3;
    private static final int LEFT = 4;

    Shark(int row, int column, int speed, int direction, int size) {
        this.row = row;
        this.column = column;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }

    public void move(int r, int c) {
        for (int i = 0; i < speed; i++) {
            if ((direction == UP && row == 0)
                || (direction == DOWN && row == r-1)
                || (direction == RIGHT && column == c-1)
                || (direction == LEFT && column == 0)) {
                reverse();
            }
            if (direction == UP) {
                row -= 1;
            }
            else if (direction == DOWN) {
                row += 1;
            }
            else if (direction == RIGHT) {
                column += 1;
            }
            else {
                column -= 1;
            }
        }
    }

    private void reverse() {
        switch (direction) {
            case UP:
                direction = DOWN;
                break;
            case DOWN:
                direction = UP;
                break;
            case RIGHT:
                direction = LEFT;
                break;
            case LEFT:
                direction = RIGHT;
                break;
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    @Override
    public String toString() {
        return row + " " + column + " " + speed + " " + direction + " " + size;
    }
}