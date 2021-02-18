import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class DFS {

    private int m;
    private int n;
    private int[][] field;
    private boolean[][] visited;
    private Map<Integer, Integer> answer = new HashMap<>();
    private int[] dx = {0, 1, 0, -1};  // clockwise
    private int[] dy = {-1, 0, 1, 0};  // clockwise

    DFS(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.field = new int[m][n];
        this.visited = new boolean[m][n];
    }

    public int getCount() {
        return this.answer.size();
    }

    public void setCabbage(int row, int column) {
        field[row][column] = 1;
    }

    public void run() {
        int id = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (field[i][j] == 0) {
                    continue;
                }
                dfs(id++, i, j);
            }
        }
    }

    private void dfs(int id, int row, int column) {
        if (visited[row][column]) {
            return;
        }
        if (field[row][column] == 0) {
            return;
        }
        visited[row][column] = true;
        answer.put(id, answer.getOrDefault(id, 0)+1);
        for (int direction = 0; direction < dx.length; direction++) {
            int nextX = column + dx[direction];
            int nextY = row + dy[direction];
            if (nextX < 0 || nextX > this.n-1 || nextY < 0 || nextY > this.m-1) {
                continue;
            }
            dfs(id, nextY, nextX);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] mnk = in.readLine().split(" ");
            int m = Integer.parseInt(mnk[0]);
            int n = Integer.parseInt(mnk[1]);
            int k = Integer.parseInt(mnk[2]);
            DFS dfs = new DFS(m, n, k);
            for (int j = 0; j < k; j++) {
                String[] each = in.readLine().split(" ");
                int row = Integer.parseInt(each[0]);
                int column = Integer.parseInt(each[1]);
                dfs.setCabbage(row, column);
            }
            dfs.run();
            answers.append(dfs.getCount()).append("\n");
        }
        in.close();
        System.out.println(answers.toString().stripTrailing());
    }
}
