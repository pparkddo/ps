import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final char PERSON = '@';
    private static final char FIRE = '*';
    private static final char WALL = '#';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static String solution(int w, int h, char[][] map) {
        Queue<Position> fires = getFires(map);
        Queue<Node> people = getPerson(map);
        boolean[][] visited = new boolean[h][w];

        while (!people.isEmpty()) {
            spread(fires, map);
            int escapeCount = escape(people, map, visited);
            if (escapeCount != -1) {
                return String.valueOf(escapeCount);
            }
        }

        return "IMPOSSIBLE";
    }

    private static Queue<Position> getFires(char[][] map) {
        Queue<Position> fires = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == FIRE) {
                    fires.add(new Position(i, j));
                }
            }
        }
        return fires;
    }

    private static Queue<Node> getPerson(char[][] map) {
        Queue<Node> person = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == PERSON) {
                    person.add(new Node(i, j, 1));
                }
            }
        }
        return person;
    }

    private static void spread(Queue<Position> fires, char[][] map) {
        int size = fires.size();
        for (int i = 0; i < size; i++) {
            Position position = fires.poll();
            final int row = position.row;
            final int column = position.column;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (map[nextRow][nextColumn] == WALL || map[nextRow][nextColumn] == FIRE) {
                    continue;
                }
                fires.add(new Position(nextRow, nextColumn));
                map[nextRow][nextColumn] = FIRE;
            }
        }
    }

    private static int escape(Queue<Node> people, char[][] map, boolean[][] visited) {
        int size = people.size();
        for (int i = 0; i < size; i++) {
            Node node = people.poll();
            final int row = node.row;
            final int column = node.column;
            final int count = node.count;
            visited[row][column] = true;
            if (canEscape(row, column, map)) {
                return count;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (map[nextRow][nextColumn] == WALL || map[nextRow][nextColumn] == FIRE) {
                    continue;
                }
                people.add(new Node(nextRow, nextColumn, count+1));
                visited[nextRow][nextColumn] = true;
            }
        }
        return -1;
    }

    private static boolean isValid(int row, int column, char[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }

    private static boolean canEscape(int row, int column, char[][] map) {
        return row == 0 || row == (map.length-1) || column == 0 || column == (map[0].length-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int t = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
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
            answers.append(solution(w, h, map)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Node {

    int row;
    int column;
    int count;

    Node(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("%d, %d(%d)", row, column, count);
    }
}

class Position {

    int row;
    int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}