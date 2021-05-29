import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int UNREACHABLE = 100 * 100_000 + 1;

    private static String convertToString(int[][] distances) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < distances.length; i++) {
            for (int j = 1; j < distances.length; j++) {
                sb.append(distances[i][j] == UNREACHABLE ? 0 : distances[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private static String solution(int n, int m, Map<Integer, List<Edge>> graph) {
        int[][] distances = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distances[i], UNREACHABLE);
            distances[i][i] = 0;
        }

        for (int v = 1; v <= n; v++) {
            for (Edge edge : graph.getOrDefault(v, new ArrayList<>())) {
                distances[v][edge.to] = Math.min(distances[v][edge.to], edge.cost);
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        return convertToString(distances);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] abc = in.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            List<Edge> edges = graph.getOrDefault(a, new ArrayList<>());
            edges.add(new Edge(b, c));
            graph.put(a, edges);
        }
        in.close();
        System.out.println(solution(n, m, graph));
    }
}

class Edge {

    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}