import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final int LAND = 1;
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static boolean isAvailable(int row, int column, int w, int h) {
        return (
            row >= 0
            && row < h
            && column >= 0
            && column < w
        );
    }

    private static void dfs(int row, int column, int[][] map, boolean[][] visited) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(nextRow, nextColumn, visited[0].length, visited.length) || map[nextRow][nextColumn] != LAND) {
                continue;
            }
            dfs(nextRow, nextColumn, map, visited);
        }
    }

    private static int solution(int w, int h, int[][] map) {
        boolean[][] visited = new boolean[h][w];
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (visited[i][j] || map[i][j] != LAND) {
                    continue;
                }
                dfs(i, j, map, visited);
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        while (true) {
            String[] wh = in.readLine().split(" ");
            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);
            if (w == 0 && h == 0) {
                break;
            }
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                String[] each = in.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(each[j]);
                }
            }
            answers.append(solution(w, h, map)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
