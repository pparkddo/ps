import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static final int MAX_EDGE_COUNT = 10_000;
    private static final int MAX_EDGE_COST = 100;
    private static final int UNREACHABLE = MAX_EDGE_COUNT * MAX_EDGE_COST + 1;

    private static int solution(int n, int m, int x, Map<Integer, List<Edge>> graph) {
        int[] distancesFromPartyTown = getDistancesFromPartyTown(x, n, graph);
        graph = reverse(graph);
        int[] distancesToPartyTown = getDistancesFromPartyTown(x, n, graph);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, distancesFromPartyTown[i] + distancesToPartyTown[i]);
        }
        return max;
    }

    private static Map<Integer, List<Edge>> reverse(Map<Integer, List<Edge>> graph) {
        Map<Integer, List<Edge>> reversed = new HashMap<>();
        for (Integer key : graph.keySet()) {
            reversed.put(key, new ArrayList<>());
        }
        for (Integer from : graph.keySet()) {
            for (Edge edge : graph.get(from)) {
                reversed.get(edge.to).add(new Edge(from, edge.cost));
            }
        }
        return reversed;
    }

    private static int[] getDistancesFromPartyTown(int x, int n, Map<Integer, List<Edge>> graph) {
        int[] distances = new int[n+1];
        Arrays.fill(distances, UNREACHABLE);

        int source = x;
        distances[source] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, distances[source]));

        boolean[] visited = new boolean[n+1];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            final int to = edge.to;
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            for (Edge each : graph.get(to)) {
                if (distances[each.to] >= distances[to] + each.cost) {
                    distances[each.to] = distances[to] + each.cost;
                    pq.add(new Edge(each.to, distances[each.to]));
                }
            }
        }

        return distances;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmx = in.readLine().split(" ");
        int n = Integer.parseInt(nmx[0]);
        int m = Integer.parseInt(nmx[1]);
        int x = Integer.parseInt(nmx[2]);
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int from = Integer.parseInt(each[0]);
            int to = Integer.parseInt(each[1]);
            int cost = Integer.parseInt(each[2]);
            graph.get(from).add(new Edge(to, cost));
        }
        in.close();
        System.out.println(solution(n, m, x, graph));
    }
}

class Edge implements Comparable<Edge> {

    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost =cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}