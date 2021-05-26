import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final char MINERAL = 'x';
    private static final char BLANK = '.';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int getMineralCount(char[][] cave) {
        int count = 0;
        for (char[] row : cave) {
            for (char each : row) {
                if (each == MINERAL) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid(int row, int column, char[][] cave) {
        return row >= 0 && row < cave.length && column >= 0 && column < cave[0].length;
    }

    private static int bfs(int initialRow, int initialColumn, char[][] cave, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn));

        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            if (visited[row][column]) {
                continue;
            }
            if (cave[row][column] == BLANK) {
                continue;
            }
            visited[row][column] = true;
            count++;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, cave)) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn));
            }
        }
        return count;
    }

    private static int getMineralCountInCluster(char[][] cave, boolean[][] visited) {
        int count = 0;
        for (int column = 0; column < cave[0].length; column++) {
            if (cave[cave.length-1][column] == BLANK) {
                continue;
            }
            count += bfs(cave.length-1, column, cave, visited);
        }
        return count;
    }

    private static int getRow(char[][] cave, int height) {
        return cave.length - height;
    }

    private static void removeMineralFromLeft(char[][] cave, int height) {
        int row = getRow(cave, height);
        for (int column = 0; column < cave[0].length; column++) {
            if (cave[row][column] == MINERAL) {
                cave[row][column] = BLANK;
                return;
            }
        }
    }

    private static void removeMineralFromRight(char[][] cave, int height) {
        int row = getRow(cave, height);
        for (int column = cave[0].length-1; column >= 0; column--) {
            if (cave[row][column] == MINERAL) {
                cave[row][column] = BLANK;
                return;
            }
        }
    }

    private static void removeMineral(char[][] cave, int height, TURN turn) {
        if (turn == TURN.LEFT) {
            removeMineralFromLeft(cave, height);
        } else {
            removeMineralFromRight(cave, height);
        }
    }

    private static List<Node> getCluster(char[][] cave, boolean[][] visited) {
        List<Node> cluster = new ArrayList<>();
        for (int row = 0; row < cave.length; row++) {
            for (int column = 0; column < cave[0].length; column++) {
                if (visited[row][column] || cave[row][column] == BLANK) {
                    continue;
                }
                cluster.add(new Node(row, column));
            }
        }
        return cluster;
    }

    private static boolean canMoveDown(List<Node> cluster, char[][] cave, boolean[][] visited, int downCount) {
        for (Node each : cluster) {
            if (each.row+downCount >= cave.length) {
                return false;
            }
            if (visited[each.row+downCount][each.column]) {
                return false;
            }
        }
        return true;
    }

    private static int getDownCount(List<Node> cluster, char[][] cave, boolean[][] visited) {
        int downCount = 0;
        while (true) {
            if (!canMoveDown(cluster, cave, visited, downCount)) {
                return downCount - 1;
            }
            downCount++;
        }
    }

    private static String convertCaveToString(char[][] cave) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                sb.append(cave[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private static void moveCluster(List<Node> cluster, char[][] cave, int downCount) {
        for (Node each : cluster) {
            cave[each.row][each.column] = BLANK;
        }
        for (Node each : cluster) {
            cave[each.row+downCount][each.column] = MINERAL;
        }
    }

    private static String solution(int r, int c, char[][] cave, int n, int[] heights) {
        TURN turn = TURN.LEFT;

        for (int height : heights) {
            removeMineral(cave, height, turn);
            turn = turn.next();

            boolean[][] visited = new boolean[cave.length][cave[0].length];
            int mineralCountInCluster = getMineralCountInCluster(cave, visited);
            if (getMineralCount(cave) != mineralCountInCluster) {  // has two or more clusters
                List<Node> cluster = getCluster(cave, visited);
                if (cluster.isEmpty()) {
                    break;
                }
                int downCount = getDownCount(cluster, cave, visited);
                moveCluster(cluster, cave, downCount);
            }
        }

        return convertCaveToString(cave);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        char[][] cave = new char[r][c];
        for (int i = 0; i < r; i++) {
            String each = in.readLine();
            for (int j = 0; j < c; j++) {
                cave[i][j] = each.charAt(j);
            }
        }
        int n = Integer.parseInt(in.readLine());
        int[] heights = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();
        System.out.println(solution(r, c, cave, n, heights));
    }
}

enum TURN {

    LEFT(true),
    RIGHT(false);

    final boolean value;

    private TURN(boolean value) {
        this.value = value;
    }
    
    public TURN next() {
        return this == LEFT ? RIGHT : LEFT;
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
