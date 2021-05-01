import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<Integer, List<Integer>> buildGraph(int n, int[][] array) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int row = 0; row < array.length; row++) {
            List<Integer> edges = new ArrayList<>();
            for (int column = 0; column < array.length; column++) {
                if (array[row][column] == 1) {
                    edges.add(column);
                }
            }
            graph.put(row, edges);
        }
        return graph;
    }

    private static void dfs(int vertex, int from, Map<Integer, List<Integer>> graph, int[][] routes) {
        if (routes[from][vertex] != 0) {
            return;
        }
        routes[from][vertex] = 1;
        for (int each : graph.getOrDefault(vertex, new ArrayList<>())) {
            dfs(each, from, graph, routes);
        }
    }

    private static String solution(int n, int[][] array) {
        Map<Integer, List<Integer>> graph = buildGraph(n, array);
        int[][] routes = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int each : graph.getOrDefault(row, new ArrayList<>())) {
                dfs(each, row, graph, routes);
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < routes.length; column++) {
                answer.append(routes[row][column]).append(" ");
            }
            answer.append("\n");
        }
        return answer.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                array[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, array));
    }
}
