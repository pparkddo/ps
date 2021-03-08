import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class DFS {

    private int[][] area;
    private int height; 
    private boolean[][] visited;
    private int numberOfSafeArea = 0;
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};

    DFS(int[][] area, int height) {
        this.area = area;
        this.height = height;
        this.visited = new boolean[this.area.length][this.area[0].length];
    }
    
    public void run() {
        for (int row = 0; row < area.length; row++) {
            for (int column = 0; column < area[0].length; column++) {
                if (isSink(row, column)) {
                    continue;
                }
                if (visited[row][column]) {
                    continue;
                }
                dfs(row, column);
                numberOfSafeArea++;
            }
        }
    }

    private boolean isPossible(int row, int column) {
        return (
            row >= 0
            && row < this.area.length
            && column >= 0
            && column < this.area[0].length
        );
    }

    private boolean isSink(int row, int column) {
        return this.area[row][column] <= this.height;
    }

    private void dfs(int row, int column) {
        visited[row][column] = true;
        for (int direction = 0; direction < dr.length; direction++) {
            int newRow = row + dr[direction];
            int newColumn = column + dc[direction];
            if (!isPossible(newRow, newColumn)) {
                continue;
            }
            if (isSink(newRow, newColumn)) {
                continue;
            }
            if (visited[newRow][newColumn]) {
                continue;
            }
            dfs(newRow, newColumn);
        }
    }

    public int getNumberOfSafeArea() {
        return numberOfSafeArea;
    }
}

public class Main {
    
    private static List<Integer> answers = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] area = new int[n][n];
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < area.length; j++) {
                int height = Integer.parseInt(each[j]);
                area[i][j] = height;
                maxHeight = Math.max(maxHeight, height);
            }
        }
        in.close();

        for (int height = 0; height <= maxHeight; height++) {
            DFS runner = new DFS(area, height);
            runner.run();
            answers.add(runner.getNumberOfSafeArea());
        }

        System.out.println(answers.stream().max(Integer::compareTo).get());
    }
}
