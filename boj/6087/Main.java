import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final char LASER = 'C';
    private static final char WALL = '*';
    private static final char UP = 0;
    private static final char RIGHT = 1;
    private static final char DOWN = 2;
    private static final char LEFT = 3;

    private static List<Laser> getLasers(char[][] map) {
        List<Laser> lasers = new ArrayList<>();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == LASER) {
                    lasers.add(new Laser(row, column));
                }
            }
        }
        if (lasers.size() != 2) new AssertionError("Invalid laser size : " + lasers.size());
        return lasers;
    }

    private static int rotateCounterClockwise(int direction) {
        return (4 + direction-1) % 4;
    }

    private static int rotateClockwise(int direction) {
        return (direction + 1) % 4;
    }

    private static boolean isValid(int row, int column, char[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }

    private static int bfs(Laser start, Laser end, char[][] map) {
        int answer = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start.row, start.column, 0, UP));
        queue.add(new Node(start.row, start.column, 0, RIGHT));
        queue.add(new Node(start.row, start.column, 0, DOWN));
        queue.add(new Node(start.row, start.column, 0, LEFT));
        int[][][] visited = new int[map.length][map[0].length][4];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int count = node.count;
            int direction = node.direction;
            if (count >= visited[row][column][direction]) {
                continue;
            }
            visited[row][column][direction] = count;
            if (row == end.row && column == end.column) {
                answer = Math.min(answer, count); 
                continue;
            }
            switch (direction) {
                case UP:
                    if (!isValid(row-1, column, map)) {
                        continue;
                    }
                    if (map[row-1][column] == WALL) {
                        continue;
                    }
                    queue.add(new Node(row-1, column, count, direction));
                    queue.add(new Node(row-1, column, count+1, rotateClockwise(direction)));
                    queue.add(new Node(row-1, column, count+1, rotateCounterClockwise(direction)));
                    break;
                case RIGHT:
                    if (!isValid(row, column+1, map)) {
                        continue;
                    }
                    if (map[row][column+1] == WALL) {
                        continue;
                    }
                    queue.add(new Node(row, column+1, count, direction));
                    queue.add(new Node(row, column+1, count+1, rotateClockwise(direction)));
                    queue.add(new Node(row, column+1, count+1, rotateCounterClockwise(direction)));
                    break;
                case DOWN:
                    if (!isValid(row+1, column, map)) {
                        continue;
                    }
                    if (map[row+1][column] == WALL) {
                        continue;
                    }
                    queue.add(new Node(row+1, column, count, direction));
                    queue.add(new Node(row+1, column, count+1, rotateClockwise(direction)));
                    queue.add(new Node(row+1, column, count+1, rotateCounterClockwise(direction)));
                    break;
                case LEFT:
                    if (!isValid(row, column-1, map)) {
                        continue;
                    }
                    if (map[row][column-1] == WALL) {
                        continue;
                    }
                    queue.add(new Node(row, column-1, count, direction));
                    queue.add(new Node(row, column-1, count+1, rotateClockwise(direction)));
                    queue.add(new Node(row, column-1, count+1, rotateCounterClockwise(direction)));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown direction : " + node.direction);
            }
        }
        return answer;
    }

    private static int solution(int w, int h, char[][] map) {
        List<Laser> lasers = getLasers(map);
        return bfs(lasers.get(0), lasers.get(1), map);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] wh = in.readLine().split(" ");
        int w = Integer.parseInt(wh[0]);
        int h = Integer.parseInt(wh[1]);
        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++) {
            String each = in.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = each.charAt(j);
            }
        }
        in.close();
        System.out.println(solution(w, h, map));
    }
}

class Laser {

    int row;
    int column;

    Laser(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Node {

    int row;
    int column;
    int count;
    int direction;

    Node(int row, int column, int count, int direction) {
        this.row = row;
        this.column = column;
        this.count = count;
        this.direction = direction;
    }
}
