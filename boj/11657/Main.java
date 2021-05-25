import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final long MAX = Long.MAX_VALUE;

    private static String solution(int n, int m, Map<Integer, List<Edge>> graph) {
        long[] distances = new long[n+1];
        Arrays.fill(distances, MAX);

        int source = 1;
        distances[source] = 0;

        boolean hasNegativeCycle = false;

        for (int loopCount = 1; loopCount <= n; loopCount++) {
            for (int v = 1; v <= n; v++) {
                for (Edge each : graph.get(v)) {
                    if (distances[v] == MAX) {
                        continue;
                    }
                    if (distances[each.to] > distances[v] + each.cost) {
                        distances[each.to] = distances[v] + each.cost;
                        if (loopCount == n) {
                            hasNegativeCycle = true;
                        }
                    }
                }
            }
        }

        if (hasNegativeCycle) {
            return "-1";
        }

        StringBuilder answer = new StringBuilder();
        for (int v = source+1; v <= n; v++) {
           answer.append(distances[v] != MAX ? distances[v] : -1).append("\n");
        }
        return answer.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int from = Integer.parseInt(each[0]);
            int to = Integer.parseInt(each[1]);
            int cost = Integer.parseInt(each[2]);
            graph.get(from).add(new Edge(to, cost));
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
