import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static List<Integer> solution(int m, int n, boolean[][] grid) {
        List<Integer> answer = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column]) {
                    continue;
                }
                answer.add(bfs(row, column, grid));
            }
        }
        return answer.stream().sorted().collect(Collectors.toList());
    }

    private static int bfs(int initialRow, int initialColumn, boolean[][] grid) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn));
        grid[initialRow][initialColumn] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            count++;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, grid)) {
                    continue;
                }
                if (grid[nextRow][nextColumn]) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn));
                grid[nextRow][nextColumn] = true;
            }
        }

        return count;
    }

    private static boolean isValid(int row, int column, boolean[][] grid) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] mnk = in.readLine().split(" ");
        int m = Integer.parseInt(mnk[0]);
        int n = Integer.parseInt(mnk[1]);
        int k = Integer.parseInt(mnk[2]);

        boolean[][] grid = new boolean[m][n];
        for (int i = 0; i < k; i++) {
            String[] x1y1x2y2 = in.readLine().split(" ");
            int x1 = Integer.parseInt(x1y1x2y2[0]);
            int y1 = Integer.parseInt(x1y1x2y2[1]);
            int x2 = Integer.parseInt(x1y1x2y2[2])-1;
            int y2 = Integer.parseInt(x1y1x2y2[3])-1;
            paintRectangle(x1, y1, x2, y2, grid);
        }
        in.close();

        List<Integer> values = solution(m, n, grid);
        System.out.println(convertToAnswerForm(values));
    }

    private static void paintRectangle(int x1, int y1, int x2, int y2, boolean[][] grid) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                grid[grid.length-1-y][x] = true;
            }
        }
    }

    private static String convertToAnswerForm(List<Integer> values) {
        StringBuilder sb = new StringBuilder();
        sb.append(values.size()).append("\n");
        sb.append(values.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        return sb.toString();
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