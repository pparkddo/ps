import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static Map<Integer, Integer> answers = new HashMap<>();

    private static void dfs(int id, int row, int column) {
        if (map[row][column] == 0) {
            return;
        }
        if (visited[row][column]) {
            return;
        }

        visited[row][column] = true;
        answers.put(id, answers.getOrDefault(id, 0)+1);

        if (row > 0) {
            dfs(id, row-1, column);
        }
        if (column > 0) {
            dfs(id, row, column-1);
        }
        if (column < map.length-1) {
            dfs(id, row, column+1);
        }
        if (row < map.length-1) {
            dfs(id, row+1, column);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        in.close();

        visited = new boolean[n][n];
        int id = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (map[i][j] == 0) {
                    continue;
                }
                dfs(id++, i, j);
            }
        }

        System.out.println(answers.size()); 
        StringBuilder output = new StringBuilder();
        for (int count: answers.values().stream().sorted().mapToInt(Integer::valueOf).toArray()) {
            output.append(count).append("\n");
        }
        System.out.println(output.toString().stripTrailing());
    }
}
