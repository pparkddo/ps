import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static final int WALL = 1;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int n, int m, int[][] maze) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 0));

        int[][] visited = new int[m][n];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        int answer = 0;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int count = node.count;
            visited[row][column] = count;
            if (row == m-1 && column == n-1) {
                answer = count;
                break;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, maze)) {
                    continue;
                }
                int nextCount = count;
                if (maze[nextRow][nextColumn] == WALL) {
                    nextCount++;
                }
                if (nextCount >= visited[nextRow][nextColumn]) {
                    continue;
                }
                visited[nextRow][nextColumn] = nextCount;
                queue.add(new Node(nextRow, nextColumn, nextCount));
            }
        }

        return answer;
    }

    private static boolean isValid(int row, int column, int[][] maze) {
        return row >= 0 && row < maze.length && column >= 0 && column < maze[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] maze = new int[m][n];
        for (int i = 0; i < m; i++) {
            String each = in.readLine();
            for (int j = 0; j < n; j++) {
                maze[i][j] = each.charAt(j) - '0';
            }
        }
        in.close();
        System.out.println(solution(n, m, maze));
    }
}

class Node implements Comparable<Node> {
    
    int row;
    int column;
    int count;

    Node(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(count, o.count);
    }
}