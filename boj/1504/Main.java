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

    private static final int MAX = 1_000 * 200_000;

    private static int getTotalCost(int n, Map<Integer, List<Edge>> graph, int source, int destination) {
        int[] distances = new int[n+1];
        Arrays.fill(distances, MAX);

        boolean[] visited = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        distances[source] = 0;
        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            if (to == destination) {  
                // distances[destination] 값만 필요하기 때문에 종료
                // 모든 정점에 대한 최소경로비용을 구하려면 이 분기문을 삭제해야함 
                break;
            }
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

        return distances[destination];
    }

    private static boolean isUnreachable(int c) {
        return c >= MAX;
    }

    private static int solution(int n, int e, Map<Integer, List<Edge>> graph, int v1, int v2) {
        int c1 = getTotalCost(n, graph, 1, v1) + getTotalCost(n, graph, v1, v2) + getTotalCost(n, graph, v2, n);
        int c2 = getTotalCost(n, graph, 1, v2) + getTotalCost(n, graph, v2, v1) + getTotalCost(n, graph, v1, n);
        if (isUnreachable(c1) && isUnreachable(c2)) {
            return -1;
        }
        if (isUnreachable(c1)){
            return c2;
        }
        if (isUnreachable(c2)) {
            return c1;
        }
        return Math.min(c1, c2);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ne = in.readLine().split(" ");
        int n = Integer.parseInt(ne[0]);
        int e = Integer.parseInt(ne[1]);
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            int c = Integer.parseInt(each[2]);
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        String[] vs = in.readLine().split(" ");
        int v1 = Integer.parseInt(vs[0]);
        int v2 = Integer.parseInt(vs[1]);
        in.close();
        System.out.println(solution(n, e, graph, v1, v2));
    }
}

class Edge implements Comparable<Edge> {

    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}
