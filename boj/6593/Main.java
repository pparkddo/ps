import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final char START = 'S';
    private static final char END = 'E';
    private static final char WALL = '#';
    private static final int TRAPPED = -1;
    private static final int[] dl = {1, -1, 0, 0, 0, 0};
    private static final int[] dr = {0, 0, 1, -1, 0, 0};
    private static final int[] dc = {0, 0, 0, 0, 1, -1};

    private static String solution(int l, int r, int c, char[][][] building) {
        Queue<Node> queue = new LinkedList<>();
        Node start = getStartNode(building);
        queue.add(start);

        boolean[][][] visited = new boolean[l][r][c];
        visited[start.level][start.row][start.column] = true;

        int answer = TRAPPED;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int level = node.level;
            final int row = node.row;
            final int column = node.column;
            final int count = node.count;
            if (building[level][row][column] == END) {
                answer = count;
                continue;
            }
            for (int d = 0; d < dl.length; d++) {
                final int nextLevel = level + dl[d];
                final int nextRow = row + dr[d];
                final int nextColumn = column + dc[d];
                if (!isValid(nextLevel, nextRow, nextColumn, building)) {
                    continue;
                }
                if (building[nextLevel][nextRow][nextColumn] == WALL) {
                    continue;
                }
                if (visited[nextLevel][nextRow][nextColumn]) {
                    continue;
                }
                queue.add(new Node(nextLevel, nextRow, nextColumn, count+1));
                visited[nextLevel][nextRow][nextColumn] = true;
            }
        }

        return answer == TRAPPED ? "Trapped!" : "Escaped in " + answer + " minute(s).";
    }

    private static boolean isValid(int l, int r, int c, char[][][] building) {
        return (
            l >= 0 && l < building.length
            && r >= 0 && r < building[0].length
            && c >= 0 && c < building[0][0].length
        );
    }

    private static Node getStartNode(char[][][] building) {
        for (int i = 0; i < building.length; i++) {
            for (int j = 0; j < building[0].length; j++) {
                for (int k = 0; k < building[0][0].length; k++) {
                    if (building[i][j][k] == START) {
                        return new Node(i, j, k, 0);
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no start point!");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        while (true) {
            String[] lrc = in.readLine().split(" ");
            int l = Integer.parseInt(lrc[0]);
            int r = Integer.parseInt(lrc[1]);
            int c = Integer.parseInt(lrc[2]);
            if (isExitFlag(l, r, c)) {
                break;
            }
            char[][][] building = new char[l][r][c];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String each = in.readLine();
                    for (int k = 0; k < c; k++) {
                        building[i][j][k] = each.charAt(k);
                    }
                }
                in.readLine();
            }
            answers.append(solution(l, r, c, building)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }

    private static boolean isExitFlag(int l, int r, int c) {
        return l == 0 && r == 0 && c == 0;
    }
}

class Node {

    int level;
    int row;
    int column;
    int count;

    Node(int level, int row, int column, int count) {
        this.level = level;
        this.row = row;
        this.column = column;
        this.count = count;
    }
}