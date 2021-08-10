import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    
    private static final int UNREACHABLE = 2000 * 10000 + 1;
    
    private int getAvailableCount(int source, Map<Integer, List<Edge>> graph, int k) {
        int[] distances = new int[graph.size()+1];
        Arrays.fill(distances, UNREACHABLE);
        distances[source] = 0;
        
        boolean[] visited = new boolean[graph.size()+1];
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, distances[source]));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            for (Edge each : graph.get(to)) {
                if (distances[each.to] > distances[to] + each.cost) {
                    distances[each.to] = distances[to] + each.cost;
                    pq.add(new Edge(each.to, distances[each.to]));
                }
            }
        }
        
        return (int) Arrays.stream(distances).filter(each -> each <= k).count();
    }
    
    public int solution(int N, int[][] road, int K) {
        Map<Integer, List<Edge>> graph = getGraph(N, road);
        return getAvailableCount(1, graph, K);
    }
    
    private Map<Integer, List<Edge>> getGraph(int n, int[][] roads) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int city = 1; city <= n; city++) {
            graph.put(city, new ArrayList<>());
        }
        for (int[] each : roads) {
            int a = each[0];
            int b = each[1];
            int cost = each[2];
            graph.get(a).add(new Edge(b, cost));
            graph.get(b).add(new Edge(a, cost));
        }
        return graph;
    }
}

class Edge implements Comparable<Edge> {
    
    int to;
    int cost;
    
    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}