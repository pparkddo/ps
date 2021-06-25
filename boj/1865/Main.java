import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int UNREACHABLE = 2500 * 10_000+ 1;

    private static String solution(int n, int m, int w, Map<Integer, List<Edge>> graph) {
        int sampleSource = 1;
        return hasNegativeCycle(sampleSource, n, graph) ? "YES" : "NO";
    }

    private static boolean hasNegativeCycle(int source, int n, Map<Integer, List<Edge>> graph) {
        boolean hasNegativeCycle = false;

        long[] distances = new long[n+1];
        Arrays.fill(distances, UNREACHABLE);

        distances[source] = 0;

        for (int loopCount = 1; loopCount <= n; loopCount++) {
            for (int v = 1; v <= n; v++) {
                for (Edge each : graph.get(v)) {
                    // Caution!! edited bellman ford
                    if (distances[each.to] > distances[v] + each.cost) {
                        distances[each.to] = distances[v] + each.cost;
                        if (loopCount == n) {
                            hasNegativeCycle = true;
                        }
                    }
                }
            }
        }

        return hasNegativeCycle;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < tc; testCase++) {
            String[] nmw = in.readLine().split(" ");
            int n = Integer.parseInt(nmw[0]);
            int m = Integer.parseInt(nmw[1]);
            int w = Integer.parseInt(nmw[2]);
            Map<Integer, List<Edge>> graph = new HashMap<>();
            for (int v = 1; v <= n; v++) {
                graph.put(v, new ArrayList<>());
            }
            for (int j = 0; j < m; j++) {
                String[] set = in.readLine().split(" ");
                int s = Integer.parseInt(set[0]);
                int e = Integer.parseInt(set[1]);
                int t = Integer.parseInt(set[2]);
                graph.get(s).add(new Edge(e, t));
                graph.get(e).add(new Edge(s, t));
            }
            for (int j = 0; j < w; j++) {
                String[] set = in.readLine().split(" ");
                int s = Integer.parseInt(set[0]);
                int e = Integer.parseInt(set[1]);
                int t = Integer.parseInt(set[2]);
                graph.get(s).add(new Edge(e, -t));
            }
            answers.append(solution(n, m, w, graph)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
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
