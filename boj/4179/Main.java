import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final char FIRE = 'F';
    private static final char WALL = '#';
    private static final char PERSON = 'J';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static String solution(int r, int c, char[][] maze, Queue<Node> routes, Queue<Node> fires) {
        boolean canEscape = false;
        int time = 1;
        boolean[][] routesVisited = new boolean[r][c];
        boolean[][] firesVisited = new boolean[r][c];
        while (true) {
            if (escape(routes, maze, routesVisited)) {
                canEscape = true;
                break;
            }
            time++;
            if (routes.isEmpty()) {
                break;
            }
            spread(fires, maze, firesVisited);
        }
        return canEscape ? String.valueOf(time) : "IMPOSSIBLE";
    }

    private static boolean escape(Queue<Node> routes, char[][] maze, boolean[][] visited) {
        int size = routes.size();
        for (int i = 0; i < size; i++) {
            Node fire = routes.poll();
            final int row = fire.row;
            final int column = fire.column;
            visited[row][column] = true;
            if (maze[row][column] == FIRE) {
                continue;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, maze)) {
                    return true;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (maze[nextRow][nextColumn] == WALL) {
                    continue;
                }
                if (maze[nextRow][nextColumn] == FIRE) {
                    continue;
                }
                routes.add(new Node(nextRow, nextColumn));
                maze[nextRow][nextColumn] = PERSON;
                visited[nextRow][nextColumn] = true;
            }
        }
        return false;
    }

    private static void spread(Queue<Node> fires, char[][] maze, boolean[][] visited) {
        int size = fires.size();
        for (int i = 0; i < size; i++) {
            Node fire = fires.poll();
            final int row = fire.row;
            final int column = fire.column;
            visited[row][column] = true;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, maze)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (maze[nextRow][nextColumn] == WALL) {
                    continue;
                }
                fires.add(new Node(nextRow, nextColumn));
                maze[nextRow][nextColumn] = FIRE;
                visited[nextRow][nextColumn] = true;
            }
        }
    }

    private static boolean isValid(int row, int column, char[][] maze) {
        return row >= 0 && row < maze.length && column >= 0 && column < maze[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        char[][] maze = new char[r][c];
        Queue<Node> routes = new LinkedList<>();
        Queue<Node> fires = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String each = in.readLine();
            for (int j = 0; j < c; j++) {
                maze[i][j] = each.charAt(j);
                if (maze[i][j] == FIRE) {
                    fires.add(new Node(i, j));
                }
                if (maze[i][j] == PERSON) {
                    routes.add(new Node(i, j));
                }
            }
        }
        in.close();
        System.out.println(solution(r, c, maze, routes, fires));
    }
}

class Node {

    int row;
    int column;

    Node(int row, int column) {
        this.row = row;
        this.column = column;
    }
}